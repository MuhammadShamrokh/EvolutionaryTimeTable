package DataTransferClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;

public class HomePageTableRowsData {
    private String m_HostName;
    private Integer m_AmountOfDays;
    private Integer m_AmountOfHours;
    private Integer m_AmountOfTeachers;
    private Integer m_AmountOfClasses;
    private Integer m_AmountOfSubjects;
    private Integer m_HardRolesCount;
    private Integer m_SoftRolesCount;
    private Integer m_SolvingUsers;
    private Integer m_MaxFitness;

    public HomePageTableRowsData(String i_Name, AmountOfObjectsCalc i_Amounts,Integer i_SolvingUsersAmount,Integer i_BestFitness)
    {
        m_HostName=i_Name;
        m_AmountOfDays=i_Amounts.getAmountOfDays();
        m_AmountOfHours=i_Amounts.getAmountOfHours();
        m_AmountOfTeachers=i_Amounts.getAmountOfTeachers();
        m_AmountOfClasses=i_Amounts.getAmountOfClasses();
        m_AmountOfSubjects=i_Amounts.getAmountOfSubjects();
        m_HardRolesCount=i_Amounts.getHardRolesCount();
        m_SoftRolesCount=i_Amounts.getSoftRolesCount();
        m_SolvingUsers=i_SolvingUsersAmount;
        m_MaxFitness=i_BestFitness;
    }
    public String getHostName() {
        return m_HostName;
    }

    public void setHostName(String i_HostName) {
        this.m_HostName = i_HostName;
    }

    public Integer getAmountOfDays() {
        return m_AmountOfDays;
    }

    public void setAmountOfDays(Integer i_AmountOfDays) {
        this.m_AmountOfDays = i_AmountOfDays;
    }

    public Integer getAmountOfHours() {
        return m_AmountOfHours;
    }

    public void setAmountOfHours(Integer i_AmountOfHours) {
        this.m_AmountOfHours = i_AmountOfHours;
    }

    public Integer getAmountOfTeachers() {
        return m_AmountOfTeachers;
    }

    public void setAmountOfTeachers(Integer i_AmountOfTeachers) {
        this.m_AmountOfTeachers = i_AmountOfTeachers;
    }

    public Integer getAmountOfClasses() {
        return m_AmountOfClasses;
    }

    public void setAmountOfClasses(Integer i_AmountOfClasses) {
        this.m_AmountOfClasses = i_AmountOfClasses;
    }

    public Integer getAmountOfSubjects() {
        return m_AmountOfSubjects;
    }

    public void setAmountOfSubjects(Integer i_AmountOfSubjects) {
        this.m_AmountOfSubjects = i_AmountOfSubjects;
    }

    public Integer getHardRolesCount() {
        return m_HardRolesCount;
    }

    public void setHardRolesCount(Integer i_HardRolesCount) {
        this.m_HardRolesCount = i_HardRolesCount;
    }

    public Integer getSoftRolesCount() {
        return m_SoftRolesCount;
    }

    public void setSoftRolesCount(Integer i_SoftRolesCount) {
        this.m_SoftRolesCount = i_SoftRolesCount;
    }

    public Integer getSolvingUsers() {
        return m_SolvingUsers;
    }

    public void setSolvingUsers(Integer i_SolvingUsers) {
        this.m_SolvingUsers = i_SolvingUsers;
    }

    public Integer getMaxFitness() {
        return m_MaxFitness;
    }

    public void setMaxFitness(Integer i_MaxFitness) {
        this.m_MaxFitness = i_MaxFitness;
    }
}
