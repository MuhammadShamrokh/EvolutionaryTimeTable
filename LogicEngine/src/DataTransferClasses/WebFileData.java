package DataTransferClasses;

import java.util.Set;

public class WebFileData {
    private Set<SubjectData> m_SubjectsData;
    private Set<TeacherData> m_TeacherData;
    private Set<ClassData> m_ClassData;
    private Set<RuleFileData> m_RuleData;


    public void setSubjectsData(Set<SubjectData> i_Subjects) {
        this.m_SubjectsData = i_Subjects;
    }

    public void setTeacherData(Set<TeacherData> i_TeacherData) {
        this.m_TeacherData = i_TeacherData;
    }

    public void setClassData(Set<ClassData> i_ClassData) {
        this.m_ClassData = i_ClassData;
    }

    public void setRuleData(Set<RuleFileData> i_RuleData) {
        this.m_RuleData = i_RuleData;
    }
}
