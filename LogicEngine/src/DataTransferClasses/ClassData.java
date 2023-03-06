package DataTransferClasses;

import DataClasses.FileInputDataClasses.Clazz;
import DataClasses.FileInputDataClasses.Study;
import DataClasses.FileInputDataClasses.Subjects;
import DataClasses.FileInputDataClasses.Teacher;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ClassData implements Comparable<ClassData>{
    private Integer m_ClassID;
    private String m_ClassName;
    private Set<SubjectData> m_ClassSubjects;


    public ClassData(Clazz i_Class, Subjects i_AllSubjects)
    {
        m_ClassID=i_Class.getId();
        m_ClassName=i_Class.getFullName();
        m_ClassSubjects=new TreeSet<>();
        fillClassSubjectsData(i_Class.getRequirements().getStudyList(),i_AllSubjects);
    }


    public Integer getClassID() {
        return m_ClassID;
    }

    private void fillClassSubjectsData(List<Study> i_StudyList, Subjects i_AllSubjects) {
        i_StudyList.forEach(study->m_ClassSubjects.add(i_AllSubjects.getSubjectDataWithSubjID(study.getSubjectID())));
    }

    @Override
    public int compareTo(ClassData i_ClassData) {
        return m_ClassID-i_ClassData.getClassID();
    }
}
