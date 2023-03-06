package DataClasses.FileInputDataClasses;

import DataTransferClasses.ClassData;
import DataTransferClasses.ClassIdNameData;
import DataTransferClasses.StudyData;
import DataTransferClasses.SubjectData;
import ParsedClasses.ETTClass;
import ParsedClasses.ETTClasses;

import java.util.*;

public class Clazzes {
    private List<Clazz> m_ClassesList;

    public Clazzes(ETTClasses i_ETTClasses)
    {
        m_ClassesList=new ArrayList<>();
        List<ETTClass> ettClasses = i_ETTClasses.getETTClass();
        for(ETTClass ettClass:ettClasses)
        {
            m_ClassesList.add(new Clazz(ettClass));
        }
    }

    public Clazzes(Clazzes i_Clazzes)
    {
        m_ClassesList=new ArrayList<>();
        List<Clazz> clazzes = i_Clazzes.getClassesList();
        for(Clazz clazz:clazzes)
        {
            m_ClassesList.add(new Clazz(clazz));
        }
    }



    @Override
    public String toString() {
        return "Clazzes{" +
                "m_ClassesList=" + m_ClassesList +
                '}';
    }

    public List<Clazz> getClassesList() {
        return m_ClassesList;
    }

    public Integer getClassesListSize(){return m_ClassesList.size();}

    public Map<Integer, Set<StudyData>> getClassID2SubjectsMap(Collection<SubjectData> i_Subjects)
    {
        Map<Integer, Set<StudyData>> retMap=new TreeMap<>();
        for(Clazz c:m_ClassesList)
        {
            Set<StudyData> classSubjectsSet=new TreeSet<>();
            List<Study> classesSubjectsIDList=c.getRequirements().getStudyList();
            for(Study i:classesSubjectsIDList)
            {
                SubjectData subject = i_Subjects.stream().filter(subj -> subj.getSubjectID() == i.getSubjectID()).findFirst().get();
                classSubjectsSet.add(new StudyData(i,subject));
            }
            retMap.put(c.getId(),classSubjectsSet);
        }
        return retMap;
    }

    public Set<ClassData> getClassesData(Subjects i_AllSubjects)
    {
        Set<ClassData> retClassesSet=new TreeSet<>();
        m_ClassesList.forEach(clazz->retClassesSet.add(new ClassData(clazz,i_AllSubjects)));
        return retClassesSet;
    }

    public Clazz getClassById(Integer i_ID)
    {
        return m_ClassesList.stream().filter(clazz->clazz.getId().equals(i_ID)).findFirst().get();
    }

    public String getClassNameById(Integer i_ID)
    {
        String name=m_ClassesList.stream().filter(clazz->clazz.getId().equals(i_ID)).findFirst().get().getFullName();
        return name;
    }

    public List<ClassIdNameData> getClassesIdNamesList()
    {
        List<ClassIdNameData> retList=new ArrayList<>();
        for(Clazz clazz:m_ClassesList)
        {
            retList.add(new ClassIdNameData(clazz));
        }
        return retList;
    }

}
