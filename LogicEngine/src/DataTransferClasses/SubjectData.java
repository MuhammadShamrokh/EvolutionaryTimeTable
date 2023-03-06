package DataTransferClasses;

import DataClasses.FileInputDataClasses.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubjectData implements Comparable<SubjectData>{

    private Integer m_SubjectID;
    private String m_SubjectName;

    public SubjectData(Subject i_Subject)
    {
        m_SubjectID=i_Subject.getId();
        m_SubjectName=i_Subject.getFullName().toString();
    }

    public Integer getSubjectID() {
        return m_SubjectID;
    }

    public String getSubjectName() {
        return m_SubjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectData that = (SubjectData) o;
        return m_SubjectID.equals(that.m_SubjectID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_SubjectID);
    }

    @Override
    public int compareTo(SubjectData i_Subject) {
        return (m_SubjectID-i_Subject.getSubjectID());
    }

}
