package DataClasses.AlgorithmData;

public class AmountOfObjectsCalc {
    private Integer m_AmountOfDays;
    private Integer m_AmountOfHours;
    private Integer m_AmountOfTeachers;
    private Integer m_AmountOfClasses;
    private Integer m_AmountOfSubjects;
    private Integer m_HardRolesCount;
    private Integer m_SoftRolesCount;
    private Integer m_LessonsInSolution;



    public AmountOfObjectsCalc(Integer i_AmountOfDays, Integer i_AmountOfHours, Integer i_AmountOfTeachers,
                               Integer i_AmountOfClasses, Integer i_AmountOfSubjects,
                               Integer i_HardRolesCount,Integer i_SoftRolesCount,
                               Integer i_LessonsInSolution)
    {
        m_AmountOfDays = i_AmountOfDays;
        m_AmountOfHours= i_AmountOfHours;
        m_AmountOfTeachers= i_AmountOfTeachers;
        m_AmountOfSubjects= i_AmountOfSubjects;
        m_AmountOfClasses= i_AmountOfClasses;
        m_HardRolesCount=i_HardRolesCount;
        m_SoftRolesCount=i_SoftRolesCount;
        m_LessonsInSolution=i_LessonsInSolution;
    }

    public Integer getAmountOfDays() {
        return m_AmountOfDays;
    }

    public Integer getAmountOfHours() {
        return m_AmountOfHours;
    }

    public Integer getAmountOfTeachers() {
        return m_AmountOfTeachers;
    }

    public Integer getAmountOfClasses() {
        return m_AmountOfClasses;
    }

    public Integer getAmountOfSubjects() {
        return m_AmountOfSubjects;
    }

    public Integer getLessonsInSolution() {
        return m_LessonsInSolution;
    }

    public Integer getHardRolesCount() {
        return m_HardRolesCount;
    }

    public Integer getSoftRolesCount() {
        return m_SoftRolesCount;
    }

    //function that calculate the maximum length of a solution array
    public Integer getMaxAmountOfLessons()
    {
        return (m_AmountOfDays * m_AmountOfHours * m_AmountOfClasses * m_AmountOfSubjects * m_AmountOfTeachers);
    }

    @Override
    public String toString() {
        return "AmountOfObjects{" +
                "AmountOfDays=" + m_AmountOfDays +
                ",AmountOfHours=" + m_AmountOfHours +
                ",AmountOfTeachers=" + m_AmountOfTeachers +
                ",AmountOfClasses=" + m_AmountOfClasses +
                ",AmountOfSubjects=" + m_AmountOfSubjects +
                ",LessonsInSolution=" + m_LessonsInSolution +
                '}';
    }
}
