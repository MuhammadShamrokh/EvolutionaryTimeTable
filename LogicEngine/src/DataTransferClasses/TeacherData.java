package DataTransferClasses;

import DataClasses.FileInputDataClasses.Subjects;
import DataClasses.FileInputDataClasses.Teacher;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TeacherData implements Comparable<TeacherData>{
    private Integer m_TeacherID;
    private String m_TeacherName;
    private Integer m_WorkingHours;
    private Set<SubjectData> m_TeacherSubjects;

    public TeacherData(Teacher i_Teacher, Subjects i_AllSubjects)
    {
        m_TeacherID=i_Teacher.getId();
        m_TeacherName=i_Teacher.getFullName();
        m_WorkingHours=i_Teacher.getTeacherWorkingHours();
        m_TeacherSubjects=new TreeSet<>();
        fillTeacherSubjectsData(i_Teacher.getSubjectsIDList(),i_AllSubjects);
    }


    public Integer getTeacherID() {
        return m_TeacherID;
    }

    private void fillTeacherSubjectsData(List<Integer> subjectsIDList, Subjects i_AllSubjects) {
        subjectsIDList.forEach(subjID->m_TeacherSubjects.add(i_AllSubjects.getSubjectDataWithSubjID(subjID)));
    }


    @Override
    public int compareTo(TeacherData i_Teacher) {
        return m_TeacherID-i_Teacher.getTeacherID();
    }
}
