package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/toHome")
public class ToHomeServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       User user = (User) req.getSession().getAttribute("user");

        req.setAttribute("allUsers", userManager.getAllUsers(user.getId()));
        req.setAttribute("allRequest", userManager.getFriendRequestUser(user.getId()));
        req.setAttribute("allFriends", userManager.getAllFriends(user.getId()));
        req.getRequestDispatcher("/userHome.jsp").forward(req,resp);




    }
}
