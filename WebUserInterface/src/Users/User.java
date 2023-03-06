package Users;

import AlgorithmClasses.eStoppingCondition;
import DataTransferClasses.*;
import Manager.LogicEngineManager;
import Threads.ActivateAlgoThread;
import WebManager.LogicEngineWrapper;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer m_ID;
    private String m_Username;
    private List<LogicEngineWrapper> m_EngineWrapperList;

    public User(Integer i_ID, String i_Username) {
        this.m_ID = i_ID;
        this.m_Username = i_Username;
        this.m_EngineWrapperList = new ArrayList<>();
    }

    public Integer getID() {
        return m_ID;
    }

    public String getUsername() {
        return m_Username;
    }



    public void addNewManager(LogicEngineManager i_LogicEngineManager)
    {
        m_EngineWrapperList.add(new LogicEngineWrapper(i_LogicEngineManager));
    }

    public boolean isManagerExist(Integer i_ManagerIndex)
    {
        boolean retValue;
        if(m_EngineWrapperList.stream().filter(wrapper-> wrapper.getEngineManager().getProblemIndex().equals(i_ManagerIndex)).count()>0)
            retValue = true;
        else
            retValue = false;
        return retValue;
    }

    public void updateAlgoReferences(Integer i_ManagerIndex,String i_InitialPopulation,String i_ReqGenerations,String i_ReqFitness,String i_ReqTimeInMinutes,
                                     String i_CrossoverName,String i_NumOfCuttingPoints,String i_CrossoverComponent,
                                     String i_SelectionType, String i_Percent, String i_PTE, String i_Elitism)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        wantedManager.updateAlgoReference(i_InitialPopulation,i_ReqGenerations,i_ReqFitness,
                i_ReqTimeInMinutes,i_CrossoverName,i_NumOfCuttingPoints,i_CrossoverComponent,i_SelectionType,i_Percent,i_PTE,i_Elitism);
    }

    public Integer getInitialPopulationByIndex(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getInitialPopulation();
    }

    public Integer getPrintingReqByIndex(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getReqGenerations();
    }
    public Integer getReqGenerations(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getReqGenerations();
    }

    public boolean getIsFileLoaded(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getIsFileLoaded();
    }

    public boolean getIsAlgoActivated(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getIsAlgoActivated();
    }

    public boolean getIsAlgoRunning(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getIsAlgoRunning();
    }


    public AlgorithmReferenceData getAlgorithmReferenceData(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getAlgoRefData();
    }

    public List<TeacherIdNameData> getTeachersIdNamesList(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getDescriptor().getTimeTable().getTeachers().getTeachersIdNamesList();
    }

    public List<ClassIdNameData> getClassIdNamesList(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getDescriptor().getTimeTable().getClazzes().getClassesIdNamesList();
    }


    public void startAlgorithmByIndex(Integer i_ProblemIndex)
    {
        LogicEngineWrapper wantedWrapper=getWrapperByProblemIndex(i_ProblemIndex);
        wantedWrapper.startAlgorithm();
    }

    public void pauseAlgorithmByIndex(Integer i_ProblemIndex)
    {
        LogicEngineWrapper wantedWrapper=getWrapperByProblemIndex(i_ProblemIndex);
        wantedWrapper.pauseAlgorithm();
    }

    public void stopAlgorithmByIndex(Integer i_ProblemIndex)
    {
        LogicEngineWrapper wantedWrapper=getWrapperByProblemIndex(i_ProblemIndex);
        wantedWrapper.stopAlgorithm();
    }

    public void setSolverToWrapperByIndex(Integer i_ProblemIndex,Solver i_Solver)
    {
        LogicEngineWrapper wantedWrapper=getWrapperByProblemIndex(i_ProblemIndex);
        wantedWrapper.setSolver(i_Solver);
    }

    public void addThreadToWrapperByIndex(Integer i_ProblemIndex,ActivateAlgoThread i_Thread)
    {
        LogicEngineWrapper wantedWrapper=getWrapperByProblemIndex(i_ProblemIndex);
        wantedWrapper.setThread(i_Thread);
    }


    public void addNewMutationToManager(Integer i_ManagerIndex,String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        wantedManager.addNewMutationToList(i_Name,i_Tupples,i_Char,i_Probability);
    }

    public ProgressData getProgressDataByIndex(Integer i_WrapperIndex)
    {
        LogicEngineWrapper wantedWrapper=getWrapperByProblemIndex(i_WrapperIndex);
        return wantedWrapper.getProgressData();
    }


    public MutationData getMutationDataByIndex(Integer i_ManagerIndex,Integer i_MutationIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getMutationDataByIndex(i_MutationIndex);
    }

    public List<MutationData> getMutationDataListByManagerIndex(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getMutationDataList();
    }

    public void deleteMutationByIndex(Integer i_ManagerIndex,Integer i_MutationIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        wantedManager.deleteMutationByIndex(i_MutationIndex);

    }

    public void updateMutationByIndex(Integer i_ManagerIndex,Integer i_MutationIndex,String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        wantedManager.updateMutation(i_MutationIndex,i_Name,i_Tupples,i_Char,i_Probability);
    }

    public void setIsFileLoaded(Integer i_ManagerIndex,Boolean i_NewVal)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        wantedManager.setIsFileLoaded(i_NewVal);
    }

    public void setStoppingConditionByIndex(Integer i_ManagerIndex, List<eStoppingCondition> i_StoppingConditionList)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        wantedManager.setStoppingCondition(i_StoppingConditionList);
    }

    public void createAndSetThread(Integer i_WrapperIndex,Integer i_ShowEvery)
    {
        LogicEngineWrapper wantedWrapper=getWrapperByProblemIndex(i_WrapperIndex);
        if(!wantedWrapper.isPausedAlgo()) {
            wantedWrapper.createAndSetThread(i_ShowEvery);
        }
    }

    public RawSolutionData getWebLessonDataListRaw(Integer i_ManagerIndex)
    {
        LogicEngineWrapper wantedWrapper=getWrapperByProblemIndex(i_ManagerIndex);
        return wantedWrapper.getWebLessonDataListRaw();
    }
    public TeacherClassSolutionData getBestSolutionByTeacherID(Integer i_ManagerIndex,Integer i_TeacherID)
    {
        LogicEngineWrapper wantedWrapper=getWrapperByProblemIndex(i_ManagerIndex);
        return wantedWrapper.getBestSolutionByTeacherID(i_TeacherID);
    }
    public TeacherClassSolutionData getBestSolutionByClassID(Integer i_ManagerIndex,Integer i_ClassID)
    {
        LogicEngineWrapper wantedWrapper=getWrapperByProblemIndex(i_ManagerIndex);
        return wantedWrapper.getBestSolutionByClassID(i_ClassID);
    }






    private LogicEngineManager getManagerByProblemIndex(Integer i_ProblemIndex)
    {
        LogicEngineManager retManager=null;
        for(LogicEngineWrapper wrapper:m_EngineWrapperList)
        {
            if(wrapper.getEngineManager().getProblemIndex().equals(i_ProblemIndex)) {
                retManager = wrapper.getEngineManager();
                break;
            }
        }
        return retManager;
    }

    private LogicEngineWrapper getWrapperByProblemIndex(Integer i_ProblemIndex)
    {
        LogicEngineWrapper retWrapper=null;
        for(LogicEngineWrapper wrapper:m_EngineWrapperList)
        {
            if(wrapper.getEngineManager().getProblemIndex().equals(i_ProblemIndex)) {
                retWrapper = wrapper;
                break;
            }
        }
        return retWrapper;
    }




}
