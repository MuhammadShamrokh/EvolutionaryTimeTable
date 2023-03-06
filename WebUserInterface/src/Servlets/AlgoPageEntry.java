package Servlets;

import Constants.Constants;
import Manager.LogicEngineManager;
import Users.PermUserManager;
import Users.TimeTableHostManager;
import Users.User;
import Utils.ServletUtils;
import Utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//url: LocalHost:8080/TimeTable/pages/homepage/algo-entry
public class AlgoPageEntry extends HttpServlet {
    private void processRequest(HttpServletRequest i_request, HttpServletResponse i_response) throws IOException {
        i_response.setContentType("text/plain;charset=UTF-8");
        String managerIndex=i_request.getParameter(Constants.MANAGER_INDEX);
        i_request.getSession().setAttribute(Constants.MANAGER_INDEX,managerIndex);
        PermUserManager permUserManager = ServletUtils.getPermUserManager(getServletContext());
        TimeTableHostManager hostManager=ServletUtils.getTimeTableInstances(getServletContext());
        String userID= SessionUtils.getUserID(i_request);
        User user= permUserManager.getUserByID(userID);
        synchronized (this) {
            if(!user.isManagerExist(Integer.parseInt(managerIndex)))
            { //user didnt solve this problem before
                LogicEngineManager userNewManager=new LogicEngineManager(hostManager.getAllInstances().get(Integer.parseInt(managerIndex)).getManager());
                userNewManager.setProblemIndex(Integer.parseInt(managerIndex));
                user.addNewManager(userNewManager);
            }
            //else means that user already solved the problem, he has it in his logicEngineManager List
        }

        i_response.getOutputStream().println(Constants.ALGO_PAGE);
        i_response.setStatus(200);
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
