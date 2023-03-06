package Servlets;

import AlgorithmClasses.eStoppingCondition;
import Constants.Constants;
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
import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.List;


//url: LocalHost:8080/TimeTable/pages/algopage/algoReferences
public class AlgoReferenceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("application/json");
        String userID = SessionUtils.getUserID(i_Request);
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


    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        String userID = SessionUtils.getUserID(i_Request);
        String managerIndex = SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager = ServletUtils.getPermUserManager(getServletContext());
        User user = permUserManager.getUserByID(userID);
        try {
            List<eStoppingCondition> stoppingConditionList=buildStoppingConditionList(i_Request);
            user.setStoppingConditionByIndex(Integer.parseInt(managerIndex),stoppingConditionList);
            user.updateAlgoReferences(Integer.parseInt(managerIndex), i_Request.getParameter(Constants.INITIAL_POPULATION), i_Request.getParameter(Constants.GENERATIONS_TEXT),
                    i_Request.getParameter(Constants.FITNESS_TEXT), i_Request.getParameter(Constants.TIME_TEXT), i_Request.getParameter(Constants.CROSSOVER_TYPE),
                    i_Request.getParameter(Constants.CROSSOVER_CUTTING), i_Request.getParameter(Constants.CROSSOVER_ASPECT), i_Request.getParameter(Constants.SELECTION_TYPE),
                    i_Request.getParameter(Constants.SELECTION_PERCENT), i_Request.getParameter(Constants.SELECTION_PTE), i_Request.getParameter(Constants.SELECTION_ELITISM));
            user.setIsFileLoaded(Integer.parseInt(managerIndex),true);
            i_Response.setStatus(200);
        } catch (Exception e) {
            i_Response.getOutputStream().println(e.getMessage());
            i_Response.setStatus(400);
        }
    }


    private List<eStoppingCondition> buildStoppingConditionList(HttpServletRequest i_Request) throws IOException {
        List<eStoppingCondition> retList=new ArrayList<>();
        String generationCheck=i_Request.getParameter(Constants.GENERATIONS_CHECK);
        String timeCheck=i_Request.getParameter(Constants.TIME_CHECK);
        String fitnessCheck=i_Request.getParameter(Constants.FITNESS_CHECK);
        String generationText=i_Request.getParameter(Constants.GENERATIONS_TEXT);
        String fitnessText= i_Request.getParameter(Constants.FITNESS_TEXT);
        String timeText=i_Request.getParameter(Constants.TIME_TEXT);
        if(generationCheck==null&&timeCheck==null&&fitnessCheck==null) {
            throw new RuntimeException("Error: please choose at least one stopping condition");
        }
        if(generationCheck!=null && generationText.isEmpty() || timeCheck!=null && timeText.isEmpty() || fitnessCheck!=null && fitnessText.isEmpty()) {
            throw new RuntimeException("Error, Please fill the value of the chosen stopping conditions");
        }
        //we fix with components events later, ill keep this "if" for now
        if(generationCheck==null && !generationText.isEmpty() || timeCheck==null && !timeText.isEmpty() || fitnessCheck==null && !fitnessText.isEmpty()) {
            throw new RuntimeException("Error, Please delete the unmarked stopping conditions text");
        }
        //if we reach here that mean that stopping conditions (marking and filling) is legal
        //note: we didnt check if the value in the text field is correct, we just checked if its filled only for now
        if(generationCheck!=null)
            retList.add(eStoppingCondition.GENERATIONS);
        if(fitnessCheck!=null)
            retList.add(eStoppingCondition.FITNESS);
        if(timeCheck!=null)
            retList.add(eStoppingCondition.TIME);
        return retList;
    }


}
