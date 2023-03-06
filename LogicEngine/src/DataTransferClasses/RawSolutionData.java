package DataTransferClasses;

import java.util.List;

public class RawSolutionData {
    private List<WebLessonData> m_RawSolution;
    private List<RuleData> m_RuleDataList;
    private Integer m_Fitness;
    private Integer m_SoftRulesAverage;
    private Integer m_HardRulesAverage;

    public RawSolutionData(List<WebLessonData> i_RawSolution,List<RuleData> i_RuleDataList,Integer i_Fitness,Integer i_SoftRuleAvg,Integer i_HardRuleAvg)
    {
        m_RawSolution=i_RawSolution;
        m_RuleDataList=i_RuleDataList;
        m_Fitness=i_Fitness;
        m_SoftRulesAverage=i_SoftRuleAvg;
        m_HardRulesAverage=i_HardRuleAvg;
    }

    public List<WebLessonData> getRawSolution() {
        return m_RawSolution;
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
