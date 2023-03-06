package Servlets;

import Users.PermUserManager;
import Users.User;
import Utils.ServletUtils;
import Utils.SessionUtils;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//url: LocalHost:8080/TimeTable/pages/algopage/otherUserMutations
public class AnotherUserMutationsServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException {
        i_Response.setContentType("application/json");
        String userID= i_Request.getParameter("otherUserID");
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        User user= permUserManager.getUserByID(userID);
        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            String json = gson.toJson(user.getMutationDataListByManagerIndex(Integer.parseInt(managerIndex)));
            out.println(json);
            out.flush();
        }
        i_Response.setStatus(200);
    }
}
