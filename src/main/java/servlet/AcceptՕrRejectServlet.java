package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/acceptOrReject")
public class AcceptՕrRejectServlet extends HttpServlet {
    UserManager userManager=new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getFromId= req.getParameter("from_id");
        String action = req.getParameter("action");
        User user = (User) req.getSession().getAttribute("user");
        int from_id= Integer.parseInt(getFromId);
        if (user!= null && action.equals("Accept")){
            userManager.addToFriendList (user.getId(), from_id);
            userManager.removeRequest(from_id, user.getId());
            resp.sendRedirect("/toHome");
        }else if(user!= null && action.equals("Reject")){
            userManager.removeRequest(user.getId(),from_id);
            resp.sendRedirect("userHome");

        }
    }
}
