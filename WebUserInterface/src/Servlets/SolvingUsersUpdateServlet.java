package Servlets;

import DataTransferClasses.HomePageTableRowsData;
import DataTransferClasses.SolverData;
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
import java.util.List;

//url: LocalHost:8080/TimeTable/pages/algopage/updateSolvingUsers
public class SolvingUsersUpdateServlet extends HttpServlet {
    private void processRequest(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException, ServletException {
        i_Response.setContentType("application/json");
        String managerIndex= SessionUtils.getManagerIndex(i_Request);
        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            TimeTableHostManager hostManager = ServletUtils.getTimeTableInstances(getServletContext());
            List<SolverData> solverDataList = hostManager.getSolversDataListByIndex(Integer.parseInt(managerIndex));
            String json = gson.toJson(solverDataList);
            out.println(json);
            out.flush();
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
