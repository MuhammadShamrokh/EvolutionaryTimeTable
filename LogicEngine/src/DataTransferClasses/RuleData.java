package DataTransferClasses;

import DataClasses.FileInputDataClasses.Rule;

public class RuleData {
    private String m_Name;
    private String m_Type;
    private Integer m_Grade;
    private Integer m_TotalHours;

    public RuleData(Rule i_Rule)
    {
        m_Name=i_Rule.getId().getRuleName();
        m_Type=i_Rule.getType().name();
        m_TotalHours= i_Rule.getTotalHours();
    }

    public String getName() {
        return m_Name;
    }

    public String getType() {
        return m_Type;
    }

    public Integer getGrade() {
        return m_Grade;
    }

    public void setGrade(Integer i_Grade) {
        this.m_Grade = i_Grade;
    }

    public Integer getTotalHours() {
        return m_TotalHours;
    }
}
