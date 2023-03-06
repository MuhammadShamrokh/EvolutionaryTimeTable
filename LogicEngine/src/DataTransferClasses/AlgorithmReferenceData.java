package DataTransferClasses;

import AlgorithmClasses.Crossover;
import AlgorithmClasses.Selection;
import AlgorithmClasses.eStoppingCondition;

import java.util.List;

public class AlgorithmReferenceData {
    private String m_Generations;
    private String m_Fitness;
    private String m_Time;
    private String m_SelectionType;
    private String m_Elitism;
    private String m_Percent;
    private String m_PTE;
    private String m_CrossoverType;
    private String m_CuttingPoints;
    private String m_Aspect;
    private String m_Initial;
    private String m_ShowEvery;

    public AlgorithmReferenceData(Integer i_InitialPopulation, Integer i_GenerationsReq, Integer i_FitnessReq, Integer i_MinutesReq,Integer i_ShowEvery,
                                  Crossover i_Crossover, Selection i_Selection,List<eStoppingCondition> i_StoppingConditionList){

        setInitialAndPrintingReq(i_InitialPopulation,i_ShowEvery);
        setStoppingConditions(i_GenerationsReq,i_FitnessReq,i_MinutesReq,i_StoppingConditionList);
        setCrossover(i_Crossover);
        setSelection(i_Selection);
    }

    private void setCrossover(Crossover i_Crossover) {
        if(i_Crossover!=null)
        {
            m_CrossoverType=i_Crossover.getName();
            m_CuttingPoints=i_Crossover.getCuttingPoints().toString();
            m_Aspect=i_Crossover.getChar().toString();
        }
        else
        {
            m_CrossoverType="";
            m_CuttingPoints="";
            m_Aspect="";
        }
    }

    private void setSelection(Selection i_Selection)
    {
        if(i_Selection!=null)
        {
            m_SelectionType=i_Selection.getType();
            m_Percent=i_Selection.getPercent().toString();
            m_PTE=i_Selection.getPTE().toString();
            m_Elitism=i_Selection.getElitism().toString();
        }
        else
        {
            m_SelectionType="";
            m_Percent="";
            m_PTE="";
            m_Elitism="";
        }
    }

    private void setStoppingConditions(Integer i_GenerationsReq, Integer i_FitnessReq, Integer i_MinutesReq,List<eStoppingCondition> i_StoppingConditionList) {

        if(i_StoppingConditionList.stream().anyMatch(stopCondition -> stopCondition.stoppingConditionName().toUpperCase().equals("GENERATION")))
            m_Generations=i_GenerationsReq.toString();
        else
            m_Generations="";


        if(i_StoppingConditionList.stream().anyMatch(stopCondition -> stopCondition.stoppingConditionName().toUpperCase().equals("FITNESS")))
            m_Fitness=i_FitnessReq.toString();
        else
            m_Fitness="";


        if(i_StoppingConditionList.stream().anyMatch(stopCondition->stopCondition.stoppingConditionName().toUpperCase().equals("TIME")))
            m_Time=i_MinutesReq.toString();
        else
            m_Time="";
    }

    private void setInitialAndPrintingReq(Integer i_InitialPopulation,Integer i_ShowEvery)
    {
        if(i_InitialPopulation!=null)
            m_Initial=i_InitialPopulation.toString();
        else
            m_Initial="";

        if(i_ShowEvery!=null)
            m_ShowEvery=i_ShowEvery.toString();
        else
            m_ShowEvery="";
    }



}
