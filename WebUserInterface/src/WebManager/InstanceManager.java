package WebManager;

import DataTransferClasses.HomePageTableRowsData;
import DataTransferClasses.SolverData;
import DataTransferClasses.WebFileData;
import Manager.LogicEngineManager;
import Users.Solver;
import Users.SolvingUserManager;

import java.util.List;

public class InstanceManager {
    private Integer m_HostID;
    private String m_HostName;
    private LogicEngineManager m_Manager;
    private SolvingUserManager m_SolvingUserManager;
    private WebFileData m_fileDataSaver;

    public InstanceManager(Integer i_HostID,String i_HostName, LogicEngineManager i_LogicEngineManager)
    {
        m_HostID=i_HostID;
        m_HostName=i_HostName;
        m_Manager=i_LogicEngineManager;
        m_SolvingUserManager=new SolvingUserManager();
        m_fileDataSaver=m_Manager.getWebFileData();
    }

    public HomePageTableRowsData getRowData()
    {
        Integer bestFitness=m_SolvingUserManager.getBestFitnessFromAllSolvers();
        HomePageTableRowsData retHomePageTableRowsData =new HomePageTableRowsData(m_HostName, m_Manager.getAmountOfData(),m_SolvingUserManager.getSolversAmount(),bestFitness);
        return retHomePageTableRowsData;
    }

    public List<SolverData> getSolversDataList()
    {
        return m_SolvingUserManager.getSolversDataList();
    }

    public synchronized void addUserToSolvingUsers(Integer i_SolverID,String i_SolverName)
    {
        if(!m_SolvingUserManager.isUserExists(i_SolverName)) {
            m_SolvingUserManager.addSolver(new Solver(i_SolverID, i_SolverName));
        }
    }

    public Solver getSolverFromSolversList(String i_SolverName)
    {
        return m_SolvingUserManager.getSolver(i_SolverName);
    }

    public synchronized void removeSolverFromSolvingUsers(String i_SolverName)
    {
        m_SolvingUserManager.removeSolver(i_SolverName);
    }
    public Integer getHostID() {
        return m_HostID;
    }

    public LogicEngineManager getManager() {
        return m_Manager;
    }

    public SolvingUserManager getSolvingManager(){return m_SolvingUserManager;}

    public WebFileData getFileDataSaver() {
        return m_fileDataSaver;
    }


}
