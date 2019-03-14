package manager;

import db.DBConnectionProvider;
import model.Message;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private Connection connection;

    public MessageManager() {

        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addMessage(Message message) {
        String query = "INSERT INTO message (`from_id`, `to_id`, `message`,`datetime`, `file`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, message.getFromId());
            preparedStatement.setLong(2, message.getToId());
            preparedStatement.setString(3, message.getMessage());
            preparedStatement.setString(4, DateUtil.convertDateToString(message.getDate()));
            preparedStatement.setString(5, message.getFile());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                message.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Message> getMessage(int fromId, int toId) {

        try {
            String query = "SELECT * FROM `message` WHERE `from_id` IN(" + fromId + "," + toId + ") AND `to_id` IN(" + fromId + "," + toId + ")";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Message> messages = new ArrayList<Message>();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setFromId(resultSet.getInt(2));
                message.setToId(resultSet.getInt(3));
                message.setMessage(resultSet.getString(4));
                message.setDate(DateUtil.convertstringToDate(resultSet.getString(5)));
                message.setFile(resultSet.getString(6));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
