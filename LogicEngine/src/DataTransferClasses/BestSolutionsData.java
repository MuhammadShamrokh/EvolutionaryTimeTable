package DataTransferClasses;

import DataClasses.AlgorithmData.Lesson;
import DataClasses.AlgorithmData.Parent;

import java.util.ArrayList;
import java.util.List;

public class BestSolutionsData {
    private List<LessonData> m_LessonsDataList;
    private Integer m_Fitness;
    private List<RuleData> m_RulesDataList;
    private Integer m_SoftRulesAverage;
    private Integer m_HardRulesAverage;

    public BestSolutionsData(Parent i_Parent, List<RuleData> i_RulesDataList, Integer i_SoftAverage, Integer i_HardAverage)
    {
        m_LessonsDataList=new ArrayList<>();
        for(Lesson lesson:i_Parent.getLessonsList())
        {
            m_LessonsDataList.add(new LessonData(lesson));
        }
        m_Fitness=i_Parent.getFitness();
        m_RulesDataList=i_RulesDataList;
        m_HardRulesAverage=i_HardAverage;
        m_SoftRulesAverage=i_SoftAverage;
    }

    public Integer getFitness() {
        return m_Fitness;
    }

    public List<LessonData> getLessonsDataList() {
        return m_LessonsDataList;
    }

    public List<RuleData> getRulesDataList() {
        return m_RulesDataList;
    }

    public Integer getSoftRulesAverage() {
        return m_SoftRulesAverage;
    }

    public Integer getHardRulesAverage() {
        return m_HardRulesAverage;
    }
}
