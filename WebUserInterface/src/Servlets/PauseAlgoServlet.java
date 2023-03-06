package Servlets;

import Users.PermUserManager;
import Users.User;
import Utils.ServletUtils;
import Utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//url: LocalHost:8080/TimeTable/pages/algopage/pause
public class PauseAlgoServlet extends HttpServlet {

    private void processRequest(HttpServletRequest i_Request,HttpServletResponse i_Response) throws IOException, ServletException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        String userID = SessionUtils.getUserID(i_Request);
        String managerIndex = SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager = ServletUtils.getPermUserManager(getServletContext());
        User user = permUserManager.getUserByID(userID);
        try {
            user.pauseAlgorithmByIndex(Integer.parseInt(managerIndex));
            i_Response.setStatus(200);
        }catch(Exception e)
        {
            i_Response.getOutputStream().println(e.getMessage());
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
