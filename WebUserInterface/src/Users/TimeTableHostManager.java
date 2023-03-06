package Users;

import DataTransferClasses.HomePageTableRowsData;
import DataTransferClasses.SolverData;
import Manager.LogicEngineManager;
import WebManager.InstanceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeTableHostManager {
    private List<InstanceManager> m_InstancesList;

    public TimeTableHostManager() {
        m_InstancesList = new ArrayList<>();
    }

    public synchronized void addInstance(InstanceManager i_LogicEngineInstance) {
        m_InstancesList.add(i_LogicEngineInstance);
    }


    public synchronized void addInstance(String i_HostID,String i_HostName, LogicEngineManager i_LogicEngineManager) {
        Integer hostID=Integer.parseInt(i_HostID);

        InstanceManager instance=new InstanceManager(hostID,i_HostName,i_LogicEngineManager);
        m_InstancesList.add(instance);
    }

    public synchronized void removeUser(Integer i_InstanceIndx) {
        m_InstancesList.remove(i_InstanceIndx.intValue());
    }

    public synchronized List<InstanceManager> getAllInstances() {
        return Collections.unmodifiableList(m_InstancesList);
    }

    public List<SolverData> getSolversDataListByIndex(Integer i_InstanceManagerIndex)
    {
        return m_InstancesList.get(i_InstanceManagerIndex).getSolversDataList();
    }
    public List<HomePageTableRowsData> getRowDataList(){
        List<HomePageTableRowsData> retList=new ArrayList<>();
        for(InstanceManager instance:m_InstancesList)
        {
            retList.add(instance.getRowData());
        }
        return Collections.unmodifiableList(retList);
    }

    public void addSolverToSolvingManager(Integer i_InstanceManagerIndex, Integer i_SolverID, String i_SolverName)
    {
        m_InstancesList.get(i_InstanceManagerIndex).addUserToSolvingUsers(i_SolverID,i_SolverName);
    }

    public Solver getSolverFromSolvingManager(Integer i_InstanceManagerIndex,String i_SolverName)
    {
        return m_InstancesList.get(i_InstanceManagerIndex).getSolverFromSolversList(i_SolverName);
    }

    public void removeSolverFromSolvingManager(Integer i_InstaceManagerIndex, String i_SolverName)
    {
        m_InstancesList.get(i_InstaceManagerIndex).removeSolverFromSolvingUsers(i_SolverName);
    }
}
