package DataClasses.FileInputDataClasses;

import DataTransferClasses.SubjectData;
import ParsedClasses.ETTSubject;
import ParsedClasses.ETTSubjects;

import java.util.*;

public class Subjects {
    private List<Subject> m_SubjectsList;

    public Subjects(ETTSubjects i_ETTSubjects)
    {
        m_SubjectsList=new ArrayList<>();
        List<ETTSubject> ettSubjects = i_ETTSubjects.getETTSubject();
        for(ETTSubject subject:ettSubjects)
        {
            m_SubjectsList.add(new Subject(subject));
        }
    }

    public Subjects(Subjects i_Subjects)
    {
        m_SubjectsList=new ArrayList<>();
        List<Subject> subjectsList=i_Subjects.getSubjectsList();
        for(Subject subject:subjectsList)
        {
            m_SubjectsList.add(new Subject(subject));
        }
    }

    public List<Subject> getSubjectsList() {
        return m_SubjectsList;
    }

    public Integer getSubjectsListSize(){return m_SubjectsList.size();}

    public Set<SubjectData> getSubjectSet(){
        Set<SubjectData> retSet=new TreeSet<>();
        m_SubjectsList.forEach(subj-> retSet.add(new SubjectData(subj)));
        return retSet;
    }

    public String getSubjectNameById(Integer i_ID)
    {
        return m_SubjectsList.stream().filter(subject->subject.getId().equals(i_ID)).findFirst().get().getFullName();
    }

    public SubjectData getSubjectDataWithSubjID(Integer i_SubjID)
    {
        Subject wantedSubj=null;
        for(Subject subj:m_SubjectsList)
        {
            if(subj.getId()==i_SubjID)
                wantedSubj=subj;
        }
        SubjectData retSubjData=new SubjectData(wantedSubj);
        return retSubjData;
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "m_SubjectsList=" + m_SubjectsList +
                '}';
    }
}
