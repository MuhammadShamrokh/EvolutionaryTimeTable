package DataTransferClasses;

import DataClasses.FileInputDataClasses.Study;

public class StudyData implements Comparable<StudyData>{
    private Integer m_SubjectID;
    private String m_SubjectName;
    private Integer m_ReqHours;

    public StudyData(Study i_Study, SubjectData i_Subject)
    {
        m_SubjectID=i_Study.getSubjectID();
        m_SubjectName=i_Subject.getSubjectName();
        m_ReqHours=i_Study.getHours();
    }

    public Integer getSubjectID() {
        return m_SubjectID;
    }

    public String getSubjectName() {
        return m_SubjectName;
    }

    public Integer getReqHours() {
        return m_ReqHours;
    }

    @Override
    public int compareTo(StudyData i_ClassSubject) {
        return (m_SubjectID-i_ClassSubject.getSubjectID());
    }
}
