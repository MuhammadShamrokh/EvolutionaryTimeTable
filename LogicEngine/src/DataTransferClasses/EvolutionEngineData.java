package DataTransferClasses;

import DataClasses.AlgorithmData.Parent;
import DataClasses.FileInputDataClasses.Rule;
import DataClasses.FileInputDataClasses.Rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EvolutionEngineData {
    private Map<Integer,Integer> m_Generation2BestFitnessMap;
    private Parent m_BestSolution;
    private List<RuleData> m_RulesDataList;
    private Integer m_SoftRulesAverage;
    private Integer m_HardRulesAverage;


    public EvolutionEngineData(){
        m_Generation2BestFitnessMap=new TreeMap<>();
        m_RulesDataList=new ArrayList<>();
    }

    public Map<Integer, Integer> getGeneration2BestFitnessMap() {
        return m_Generation2BestFitnessMap;
    }

    public BestSolutionsData getBestSolutionData() {
        return new BestSolutionsData(m_BestSolution,m_RulesDataList,m_SoftRulesAverage,m_HardRulesAverage);
    }

    public Integer getBestSolutionFitness()
    {
        return m_BestSolution.getFitness();
    }

    public void setBestSolution(Parent i_BestSolution) {
        m_BestSolution = i_BestSolution;
    }

    public void addToGeneration2BestFitnessMap(Integer i_GenerationCount, Integer i_Fitness)
    {
        m_Generation2BestFitnessMap.put(i_GenerationCount,i_Fitness);
    }

    public void printMap()
    {
        for(Integer generationNum: m_Generation2BestFitnessMap.keySet())
            System.out.println("Generation: "+generationNum+" Best Fitness: "+m_Generation2BestFitnessMap.get(generationNum));

    }

    public void addToRulesDataList(Rule i_Rule, Integer i_Grade)
    {
        RuleData temp=new RuleData(i_Rule);
        temp.setGrade(i_Grade);
        m_RulesDataList.add(temp);
    }

    private void calcSoftRulesAverage() {
        Integer counter=0;
        Integer sum=0;
        for(RuleData rule:m_RulesDataList)
        {
            if(rule.getType()=="SOFT")
            {
                counter++;
                sum+=rule.getGrade();
            }
        }
        m_SoftRulesAverage=sum/counter;
    }

    private void calcHardRulesAverage() {
        Integer counter=0;
        Integer sum=0;
        for(RuleData rule:m_RulesDataList)
        {
            if(rule.getType()=="HARD")
            {
                counter++;
                sum+=rule.getGrade();
            }
        }
        m_HardRulesAverage=sum/counter;
    }

    public void updateRulesAverage()
    {
        calcHardRulesAverage();
        calcSoftRulesAverage();
    }

    public Integer getSoftRulesAverage() {
        return m_SoftRulesAverage;
    }

    public Integer getHardRulesAverage() {
        return m_HardRulesAverage;
    }

    public Parent getBestSolution() {
        return m_BestSolution;
    }
}
