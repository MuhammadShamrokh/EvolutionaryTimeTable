package DataClasses.FileInputDataClasses;

import ParsedClasses.ETTTeacher;
import ParsedClasses.ETTTeaches;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher {
    private final Integer m_Id;
    private String m_FullName;
    private Integer m_TeacherHours;
    private List<Integer> m_SubjectsIDList;

    public Teacher(ETTTeacher i_ETTTeacher)
    {
        m_Id=i_ETTTeacher.getId();
        m_FullName=i_ETTTeacher.getETTName();
        m_TeacherHours=i_ETTTeacher.getETTWorkingHours();
        m_SubjectsIDList=new ArrayList<>();
        List<ETTTeaches> ettSubjects = i_ETTTeacher.getETTTeaching().getETTTeaches();
        for(ETTTeaches subject:ettSubjects)
        {
            m_SubjectsIDList.add(subject.getSubjectId());
        }
    }

    public Teacher(Teacher i_Teacher)
    {
        m_Id=i_Teacher.getId();
        m_FullName=i_Teacher.getFullName();
        m_TeacherHours=i_Teacher.getTeacherWorkingHours();
        m_SubjectsIDList=new ArrayList<>();
        List<Integer> subjectsID = i_Teacher.getSubjectsIDList();
        m_SubjectsIDList.addAll(subjectsID);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "m_FullName=" + m_FullName +
                ", m_SubjectsIDList=" + m_SubjectsIDList +
                ", m_Id=" + m_Id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return m_Id.equals(teacher.m_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Id);
    }

    public String getFullName() {
        return m_FullName;
    }

    public Integer getAmountOfSubjectsTeacherTeach()
    {
        return m_SubjectsIDList.size();
    }

    public List<Integer> getSubjectsIDList() {
        return m_SubjectsIDList;
    }

    public Integer getId() {
        return m_Id;
    }

    public Integer getTeacherWorkingHours() { return m_TeacherHours; }
}
