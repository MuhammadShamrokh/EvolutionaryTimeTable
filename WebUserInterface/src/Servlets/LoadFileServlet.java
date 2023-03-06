package Servlets;

import Manager.LogicEngineManager;
import Users.PermUserManager;
import Users.TimeTableHostManager;
import Utils.ServletUtils;
import Utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Scanner;

//localhost:8080/TimeTable/pages/homepage/loadfile
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class LoadFileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("text/html");
        Collection<Part> parts= i_Request.getParts();
        for(Part part:parts)
        {
            //converting the received file content(from client) into string
            TimeTableHostManager hostManager= ServletUtils.getTimeTableInstances(getServletContext());
            PermUserManager permUserManager =ServletUtils.getPermUserManager(getServletContext());
            LogicEngineManager logicEngineManager=new LogicEngineManager();
            try {
                logicEngineManager.LoadFile(part.getInputStream());
                String userID=SessionUtils.getUserID(i_Request);
                String userName= permUserManager.getUserNameByID(userID);
                hostManager.addInstance(userID,userName,logicEngineManager);
                i_Response.setStatus(200);
                i_Response.getOutputStream().println("File has been loaded successfully!");
            } catch (JAXBException e) {
                i_Response.setStatus(401);
                i_Response.getOutputStream().println(e.getMessage());
                break;

            } catch(RuntimeException e){
                i_Response.setStatus(400);
                i_Response.getOutputStream().println(e.getMessage());
                break;
            }

        }

    }

    private String readFromInputStream(InputStream inputStream) {
        return new Scanner(inputStream).useDelimiter("\\Z").next();
    }

}
