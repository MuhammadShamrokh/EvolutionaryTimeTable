package DataClasses.FileInputDataClasses;

import ParsedClasses.ETTStudy;

import java.util.Objects;

public class Study{
    private final Integer m_SubjectId;
    private Integer m_Hours;

    public Study(ETTStudy i_ETTStudy)
    {
        m_SubjectId=i_ETTStudy.getSubjectId();
        m_Hours=i_ETTStudy.getHours();
    }

    public Study(Study i_Study)
    {
        m_SubjectId=i_Study.getSubjectID();
        m_Hours=i_Study.getHours();
    }



    @Override
    public String toString() {
        return "Study{" +
                "m_SubjectId=" + m_SubjectId +
                ", m_Hours=" + m_Hours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Study study = (Study) o;
        return m_SubjectId.equals(study.m_SubjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_SubjectId);
    }

    public Integer getSubjectID() {
        return m_SubjectId;
    }

    public Integer getHours() {
        return m_Hours;
    }

}
