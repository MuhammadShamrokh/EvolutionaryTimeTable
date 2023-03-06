package DataClasses.FileInputDataClasses;

import DataTransferClasses.SubjectData;
import DataTransferClasses.TeacherData;
import DataTransferClasses.TeacherIdNameData;
import ParsedClasses.ETTTeacher;
import ParsedClasses.ETTTeachers;

import java.util.*;

public class Teachers {
    private List<Teacher> m_TeachersList;

    public Teachers(ETTTeachers i_ETTTeachers)
    {
        m_TeachersList=new ArrayList<>();
        List<ETTTeacher> ettTeachers = i_ETTTeachers.getETTTeacher();
        for(ETTTeacher teacher:ettTeachers)
        {
            m_TeachersList.add(new Teacher(teacher));
        }
    }

    public Teachers(Teachers i_Teachers)
    {
        m_TeachersList=new ArrayList<>();
        List<Teacher> teachers = i_Teachers.getTeachersList();
        for(Teacher teacher:teachers)
        {
            m_TeachersList.add(new Teacher(teacher));
        }
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "m_TeachersList=" + m_TeachersList +
                '}';
    }

    public List<Teacher> getTeachersList() {
        return m_TeachersList;
    }

    public Integer getTeacherListSize(){return m_TeachersList.size();}

    public Set<TeacherData> getTeachersData(Subjects i_AllSubjects)
    {
        Set<TeacherData> retTeacherSet=new TreeSet<>();
        m_TeachersList.forEach(teacher->retTeacherSet.add(new TeacherData(teacher,i_AllSubjects)));
        return retTeacherSet;
    }

    public Map<Integer, Set<SubjectData>> getTeacherID2SubjectsMap(Collection<SubjectData> i_SubjectSet)
    {
        Map<Integer, Set<SubjectData>> retMap=new TreeMap<>();
        for(Teacher t:m_TeachersList)
        {
            Set<SubjectData> teacherSubjectsSet=new TreeSet<>();
            List<Integer> teacherSubjectsIDList=t.getSubjectsIDList();
            for(Integer i:teacherSubjectsIDList) {
                teacherSubjectsSet.add(i_SubjectSet.stream().filter(subject -> subject.getSubjectID() == i).findFirst().get());
            }
            retMap.put(t.getId(),teacherSubjectsSet);
        }
        return  retMap;
    }

    public Teacher getTeacherById(Integer i_ID)
    {
        return m_TeachersList.stream().filter(teacher->teacher.getId().equals(i_ID)).findFirst().get();
    }

    public String getTeacherNameById(Integer i_ID)
    {
        return m_TeachersList.stream().filter(teacher->teacher.getId().equals(i_ID)).findFirst().get().getFullName();
    }

    public List<TeacherIdNameData> getTeachersIdNamesList()
    {
        List<TeacherIdNameData> retList=new ArrayList<>();
        for(Teacher teacher:m_TeachersList)
        {
            retList.add(new TeacherIdNameData(teacher));
        }
        return retList;
    }
}
