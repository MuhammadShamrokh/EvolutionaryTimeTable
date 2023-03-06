package Servlets;

import WebManager.InstanceManager;
import Users.TimeTableHostManager;
import Utils.ServletUtils;
import Utils.SessionUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//url: LocalHost:8080/TimeTable/pages/algopage/filedata
public class FileDataServlet extends HttpServlet {
    private void processRequest(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException, ServletException {
        i_Response.setContentType("application/json");
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        TimeTableHostManager timeTableHostManager=ServletUtils.getTimeTableInstances(getServletContext());
        InstanceManager instanceManager=timeTableHostManager.getAllInstances().get(Integer.parseInt(managerIndex));
        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            String json = gson.toJson(instanceManager.getFileDataSaver());
            out.println(json);
            out.flush();
        }

        i_Response.setStatus(200);
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
