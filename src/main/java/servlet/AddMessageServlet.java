package servlet;

import manager.MessageManager;
import model.Message;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/message")
public class AddMessageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageManager messageManager = new MessageManager();
        User user = (User) req.getSession().getAttribute("user");
//        UserManager userManager = new UserManager();
//        String friendId= req.getParameter("friendId");
//        String message= req.getParameter("message");
//        User userById = userManager.getUserById(Integer.parseInt(friendId));
//        Message message1 = new Message();
//        message1.setFromId(userById.getId());
//        message1.setToId(Integer.parseInt(friendId));
//        message1.setMessage(message);
//        message1.setDate(new Date());
//        messageManager.addMessage(message1);
//        resp.sendRedirect("/userHome");
        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 1024);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(1024 * 1024 * 50);
            upload.setSizeMax(1024 * 1024 * 5 * 50);
            String uploadPath = "C:\\Users\\USER\\IdeaProjects\\SocialNetWork1\\uploadImage\\";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Message message = new Message();

                List<FileItem> formItems = upload.parseRequest(req);
                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            String fileName = System.currentTimeMillis() + "_" + new File(item.getName()).getName();
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                            message.setFile(fileName);
                        } else {
                            if (item.getFieldName().equals("text")) {
                                message.setMessage(item.getString());
                            } else if (item.getFieldName().equals("friendId")) {
                                message.setFromId(Integer.parseInt(item.getString()));
                            }
                        }
                    }
                }

                message.setDate(new Date());


                messageManager.addMessage(message);
                req.getRequestDispatcher("/user/userHome").forward(req, resp);
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}

