package DataClasses.FileInputDataClasses;

import ParsedClasses.*;

import java.util.*;


public class CheckValidData {
    private ETTDescriptor m_Descriptor;
    private Set<Integer> m_SubjIDSet;
    private Set<Integer> m_TeachersIDSet;
    private Set<Integer> m_ClassIDSet;

    //constructor
    public CheckValidData(ETTDescriptor i_Descriptor)
    {
        m_Descriptor=i_Descriptor;
        m_SubjIDSet=new TreeSet<>();
        m_TeachersIDSet=new TreeSet<>();
        m_ClassIDSet=new TreeSet<>();
    }


    //main checking method
    public void checkFile() throws RuntimeException{
        checkSubjectsId();
        checkTeachersId();
        checkClassId();
        checkRuleAppearOnce();
        checkTeacherSubjects();
        checkClassSubjects();
        checkLimitHours();
        checkTeacherTeachingHours();
    }




    //a method to check subjects id (if they appear in order and no Duplication)
    private void checkSubjectsId() throws RuntimeException {
        List<ETTSubject> subjList= m_Descriptor.getETTTimeTable().getETTSubjects().getETTSubject();
        int subListLength=subjList.size();

        for(ETTSubject s:subjList) {
            if(m_SubjIDSet.contains(s.getId()))
                throw new RuntimeException("There is more than one Subject with same id!");
            m_SubjIDSet.add(s.getId());
        }

        for(int i=1;i<=subListLength;i++) {
            if (!m_SubjIDSet.contains(i))
                throw new RuntimeException("Subject's ID doesnt appear in serial order!");
        }

    }

    //a method to check teachers id (if they appear in order and no Duplication)
    private void checkTeachersId() throws RuntimeException {
        List<ETTTeacher> teachersList= m_Descriptor.getETTTimeTable().getETTTeachers().getETTTeacher();
        int teachersListLength=teachersList.size();

        for(ETTTeacher t:teachersList) {
            if(m_TeachersIDSet.contains(t.getId()))
                throw new RuntimeException("There is more than one Teacher with same id!");
            m_TeachersIDSet.add(t.getId());
        }

        for(int i=1;i<=teachersListLength;i++) {
            if (!m_TeachersIDSet.contains(i))
                throw new RuntimeException("Teacher's ID doesn't appear in serial order!");
        }
    }

    //a method to check class id (if they appear in order and no Duplication)
    private void checkClassId() throws RuntimeException {
        List<ETTClass> classList= m_Descriptor.getETTTimeTable().getETTClasses().getETTClass();
        int classListLength=classList.size();

        for(ETTClass c:classList) {
            if(m_ClassIDSet.contains(c.getId()))
                throw new RuntimeException("There is more than one Class with same id!");
            m_ClassIDSet.add(c.getId());
        }

        for(int i=1;i<=classListLength;i++) {
            if (!m_ClassIDSet.contains(i))
                throw new RuntimeException("Classes ID doesnt appear in serial order!");
        }
    }

    //a method to check if same rule doesnt appear Twice
    private void checkRuleAppearOnce() throws RuntimeException
    {
        List<ETTRule> ruleList= m_Descriptor.getETTTimeTable().getETTRules().getETTRule();
        List<ETTRule> rulesCountedAlready=new ArrayList<>();
        for(ETTRule ruleToCheck:ruleList) {
            for(ETTRule countedRule:rulesCountedAlready)
            {
                if(ruleToCheck.getETTRuleId().equals(countedRule.getETTRuleId()))
                    throw new RuntimeException(("Two Rules with the name ["+ruleToCheck.getETTRuleId()+"]"));
            }
            rulesCountedAlready.add(ruleToCheck);
        }
    }

    //a method to check if all the subjects that teacher teach do exist
    private void checkTeacherSubjects() throws RuntimeException
    {
        List<ETTTeacher> teachersList= m_Descriptor.getETTTimeTable().getETTTeachers().getETTTeacher();
        teachersList.forEach(this::scanTeacherSubjects);

    }

    private void scanTeacherSubjects(ETTTeacher i_Teacher) throws RuntimeException
    {
        List<ETTTeaches> subjectsList = i_Teacher.getETTTeaching().getETTTeaches();
        for(ETTTeaches t:subjectsList)
        {
            if(!m_SubjIDSet.contains(t.getSubjectId()))
                throw new RuntimeException("The teacher "+i_Teacher.getETTName()+" with id " +i_Teacher.getId()
                +" teaches an unknown subject");
        }
    }

    //a method to check if all the subjects that the class learn does exist
    private void checkClassSubjects() throws RuntimeException
    {
        List<ETTClass> classList= m_Descriptor.getETTTimeTable().getETTClasses().getETTClass();
        classList.forEach(this::scanClassSubjects);

    }

    private void scanClassSubjects(ETTClass i_Class) throws RuntimeException
    {
        List<ETTStudy> subjectsList = i_Class.getETTRequirements().getETTStudy();
        for(ETTStudy s:subjectsList)
        {
            if(!m_SubjIDSet.contains(s.getSubjectId()))
                throw new RuntimeException("The class "+i_Class.getETTName()+" with id " +i_Class.getId()
                        +" learns an unknown subject");
        }
    }

    //a method that check that the studying hours for each class doesnt step the limit of the legal studying hours
    private void checkLimitHours() throws RuntimeException
    {
        List<ETTClass> classList= m_Descriptor.getETTTimeTable().getETTClasses().getETTClass();
        int limitHours=m_Descriptor.getETTTimeTable().getDays()*m_Descriptor.getETTTimeTable().getHours();
        for(ETTClass c:classList)
        {
            int hours=c.getETTRequirements().getETTStudy().stream().mapToInt(study->study.getHours()).sum();
            if(hours>limitHours)
                throw new RuntimeException("The class "+c.getETTName()+" with id " +c.getId()
                        +" learns more than the limit hours");
        }

    }


    private void checkTeacherTeachingHours() {
        int dayInWeek=m_Descriptor.getETTTimeTable().getDays();
        int hoursInDay=m_Descriptor.getETTTimeTable().getHours();
        int totalWorkingHoursAvailable=dayInWeek*hoursInDay;
        List<ETTTeacher> teachersListInFile=m_Descriptor.getETTTimeTable().getETTTeachers().getETTTeacher();
        for(ETTTeacher t:teachersListInFile)
        {
            if(t.getETTWorkingHours()>totalWorkingHoursAvailable)
                throw new RuntimeException("The teacher "+t.getETTName()+" preferred working hours are more than available");
        }
    }










}
