package servlet;

import manager.UserManager;
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
import java.util.List;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    UserManager userManager = new UserManager();
    User user = new User();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String picUrl = req.getParameter("picUrl");

        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPicUrl(picUrl);
        req.setAttribute("message", "You have registered successfully! You can login now    ");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 1024);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(1024 * 1024 * 10000);
            upload.setSizeMax(1024 * 1024 * 5 * 10000);
            String uploadPath = "C:\\Users\\USER\\IdeaProjects\\SocialNetWork1\\uploadImage\\";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {

                List<FileItem> formItems = upload.parseRequest(req);
                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            String fileName = System.currentTimeMillis() + "_" + new File(item.getName()).getName();
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                            user.setPicUrl(fileName);
                        } else {
                            if (item.getFieldName().equals("name")) {
                                user.setName(item.getString());
                            } else if (item.getFieldName().equals("surname")) {
                                user.setSurname(item.getString());
                            } else if (item.getFieldName().equals("email")) {
                                user.setEmail(item.getString());
                            } else if (item.getFieldName().equals("password")) {
                                user.setPassword(item.getString());
                            }
                        }
                    }

                    userManager.addUser(user);
                    resp.sendRedirect("/index.jsp");
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

