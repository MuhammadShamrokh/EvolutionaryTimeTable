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

//url: LocalHost:8080/TimeTable/pages/loginpage/login
public class LoginServlet extends HttpServlet{

    private void processRequest(HttpServletRequest i_Request,HttpServletResponse i_Response) throws IOException, ServletException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        OnlineUsersManager onlineUserManager= ServletUtils.getOnlineUserManager(getServletContext());
        PermUserManager permUserManager=ServletUtils.getPermUserManager(getServletContext());

        String newBrowserUserName =i_Request.getParameter(Constants.USERNAME);
        if(newBrowserUserName ==null || newBrowserUserName.isEmpty())
        {
            i_Response.setStatus(401);
            i_Response.getOutputStream().println("Error: Please enter username");
        }
        else {
            newBrowserUserName = newBrowserUserName.trim();
            synchronized (this)
            {
                if(!onlineUserManager.isUserExists(newBrowserUserName))
                {
                    onlineUserManager.addUser(newBrowserUserName);
                    i_Request.getSession(true).setAttribute(Constants.USERNAME,newBrowserUserName);
                    //checking if the user exist in the permUserManager, which means he already logged into our site in the past
                    if(!permUserManager.isUserExists(newBrowserUserName))
                        permUserManager.addUser(newBrowserUserName);
                    //when we reach here, the user has a perm instance with id and logicEngineArray in our website
                    i_Request.getSession().setAttribute(Constants.USER_ID,permUserManager.getUserIdByName(newBrowserUserName));
                    i_Response.setStatus(200);
                    i_Response.getOutputStream().println(Constants.HOME_PAGE_PATH);
                }
                else
                {
                    String error="Error: Username "+newBrowserUserName+" already exist";
                    i_Response.setStatus(401);
                    i_Response.getOutputStream().println(error);
                }
            }
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
