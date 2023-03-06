package Servlets;

import Constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//url: LocalHost:8080/TimeTable/pages/algopage/algo-leave
public class AlgoPageLeavingServlet extends HttpServlet {
    private void processRequest(HttpServletRequest i_request, HttpServletResponse i_response) throws IOException {
        i_response.setContentType("html/plain;charset=UTF-8");
        i_response.sendRedirect(Constants.HOME_PAGE_PATH);
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
