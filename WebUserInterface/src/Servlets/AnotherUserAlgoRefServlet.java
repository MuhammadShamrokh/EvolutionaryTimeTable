package Servlets;

import DataTransferClasses.AlgorithmReferenceData;
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

//url: LocalHost:8080/TimeTable/pages/algopage/otherUserAlgoRef
public class AnotherUserAlgoRefServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("application/json");
        String userID = i_Request.getParameter("otherUserID");
        String managerIndex = SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager = ServletUtils.getPermUserManager(getServletContext());
        User user = permUserManager.getUserByID(userID);
        AlgorithmReferenceData algorithmData=user.getAlgorithmReferenceData(Integer.parseInt(managerIndex));
        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            String json = gson.toJson(algorithmData);
            out.println(json);
            out.flush();
        }
        i_Response.setStatus(200);
    }
}
