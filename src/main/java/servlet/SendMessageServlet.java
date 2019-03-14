package servlet;

import manager.MessageManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/sendMessage")
public class SendMessageServlet extends HttpServlet {
    MessageManager messageManager= new MessageManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String messageId = req.getParameter("id");
        req.setAttribute("message", messageManager.getMessage(user.getId(),Integer.parseInt(messageId)));
        req.getRequestDispatcher("/message.jsp").forward(req, resp);
    }
}
