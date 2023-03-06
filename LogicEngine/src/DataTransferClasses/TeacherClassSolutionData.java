package DataTransferClasses;

import java.util.List;

public class TeacherClassSolutionData {
    private List<DayWebLessonsData> m_Solution;
    private List<RuleData> m_RuleDataList;
    private Integer m_Fitness;
    private Integer m_SoftRulesAverage;
    private Integer m_HardRulesAverage;

    public TeacherClassSolutionData(List<DayWebLessonsData> i_BestSolution, List<RuleData> i_RuleDataList, Integer i_Fitness, Integer i_SoftRuleAvg, Integer i_HardRuleAvg)
    {
        m_Solution= i_BestSolution;
        m_RuleDataList=i_RuleDataList;
        m_Fitness=i_Fitness;
        m_SoftRulesAverage=i_SoftRuleAvg;
        m_HardRulesAverage=i_HardRuleAvg;
    }

    public List<DayWebLessonsData> getSolution() {
        return m_Solution;
    }

    public List<RuleData> getRuleDataList() {
        return m_RuleDataList;
    }

    public Integer getFitness() {
        return m_Fitness;
    }

    public Integer getSoftRulesAverage() {
        return m_SoftRulesAverage;
    }

    public Integer getHardRulesAverage() {
        return m_HardRulesAverage;
    }
}
