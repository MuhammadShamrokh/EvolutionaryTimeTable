package Servlets;

import Constants.Constants;
import Users.OnlineUsersManager;
import Users.PermUserManager;
import Utils.ServletUtils;
import Utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//LocalHost:8080/TimeTable/pages/homepage/welcome
public class WelcomeServlet extends HttpServlet {
    private void processRequest(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException, ServletException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        String userID= SessionUtils.getUserID(i_Request);
        if(userID!=null)
        {
            i_Response.getOutputStream().println(permUserManager.getUserNameByID(userID));
            i_Response.setStatus(200);
        }
        else
        { //something wrong happened, client reached homepage without a session!
            i_Response.getOutputStream().println(Constants.LOGIN_PAGE_PATH);
            i_Response.setStatus(400);
        }
    }





    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        processRequest(i_Request, i_Response);
    }
    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        processRequest(i_Request, i_Response);
    }
}
