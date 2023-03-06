package DataClasses.FileInputDataClasses;

import ParsedClasses.ETTSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subject {
    private String m_FullName;
    private final Integer m_Id;

    public Subject(ETTSubject i_ETTSubject)
    {
        m_Id=i_ETTSubject.getId();
        m_FullName=i_ETTSubject.getName();
    }

    public Subject(Subject i_Subject)
    {
        m_Id=i_Subject.getId();
        m_FullName=i_Subject.getFullName();
    }

    @Override
    public String toString() {
        return "Subject{" +
                "m_FullName=" + m_FullName +
                ", m_Id=" + m_Id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return m_Id.equals(subject.m_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Id);
    }

    public String getFullName() {
        return m_FullName;
    }

    public Integer getId() {
        return m_Id;
    }
}
