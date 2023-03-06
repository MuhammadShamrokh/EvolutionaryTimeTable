package Servlets;

import Constants.Constants;
import DataTransferClasses.MutationData;
import Users.PermUserManager;
import Users.User;
import Utils.ServletUtils;
import Utils.SessionUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MutationUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("application/json");
        String mutationIndex=i_Request.getParameter(Constants.MUTATION_INDEX);
        i_Request.getSession().setAttribute(Constants.MUTATION_INDEX,mutationIndex);
        String userID= SessionUtils.getUserID(i_Request);
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        User user= permUserManager.getUserByID(userID);
        MutationData mutationData=user.getMutationDataByIndex(Integer.parseInt(managerIndex),Integer.parseInt(mutationIndex));
        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            String json = gson.toJson(mutationData);
            out.println(json);
            out.flush();
        }
        i_Response.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        String name=i_Request.getParameter("mutationName");
        String probability=i_Request.getParameter("mutationProbability");
        String component=i_Request.getParameter("mutationComponent");
        String tupples=i_Request.getParameter("mutationTupples");
        String mutationIndex=i_Request.getSession().getAttribute(Constants.MUTATION_INDEX).toString();
        String userID= SessionUtils.getUserID(i_Request);
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        User user= permUserManager.getUserByID(userID);
        try
        {
            user.updateMutationByIndex(Integer.parseInt(managerIndex),Integer.parseInt(mutationIndex),name,tupples,component,probability);
            i_Response.getOutputStream().println("Mutation has been updated successfully");
            i_Response.setStatus(200);
        }
        catch(Exception e) {
            i_Response.getOutputStream().println(e.getMessage());
            i_Response.setStatus(400);
        }
    }

}
