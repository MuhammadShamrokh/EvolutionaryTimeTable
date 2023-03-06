package DataClasses.AlgorithmData;


import java.util.Objects;

public class Lesson {
    private Integer m_Day;
    private Integer m_Hour;
    private Integer m_ClassID;
    private Integer m_TeacherID;
    private Integer m_SubjectID;

    public Lesson(Integer i_Day,Integer i_Hour,
                  Integer i_ClassID,Integer i_TeacherID,
                  Integer i_SubjectID)
    {
        m_Day= i_Day;
        m_Hour=i_Hour;
        m_ClassID= i_ClassID;
        m_TeacherID= i_TeacherID;
        m_SubjectID= i_SubjectID;
    }

    public Lesson(Lesson i_Lesson)
    {
        m_Day=i_Lesson.getDay();
        m_Hour=i_Lesson.getHour();
        m_ClassID=i_Lesson.getClassID();
        m_TeacherID=i_Lesson.getTeacherID();
        m_SubjectID=i_Lesson.m_SubjectID;
    }


    public Integer getDay() {
        return m_Day;
    }

    public void setDay(Integer i_Day) {
        this.m_Day = i_Day;
    }

    public Integer getHour() {
        return m_Hour;
    }

    public void setHour(Integer i_Hour) {
        this.m_Hour = i_Hour;
    }

    public Integer getClassID() {
        return m_ClassID;
    }

    public void setClassID(Integer i_ClassID) {
        this.m_ClassID = i_ClassID;
    }

    public Integer getTeacherID() {
        return m_TeacherID;
    }

    public void setTeacherID(Integer i_TeacherID) {
        this.m_TeacherID = i_TeacherID;
    }

    public Integer getSubjectID() {
        return m_SubjectID;
    }

    public void setSubjectID(Integer i_SubjectID) {
        this.m_SubjectID = i_SubjectID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(m_Day, lesson.m_Day) && Objects.equals(m_Hour, lesson.m_Hour)
                && Objects.equals(m_ClassID, lesson.m_ClassID) && Objects.equals(m_TeacherID, lesson.m_TeacherID)
                && Objects.equals(m_SubjectID, lesson.m_SubjectID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Day, m_Hour, m_ClassID, m_TeacherID, m_SubjectID);
    }

    //**

    @Override
    public String toString() {
        return "Lesson{" +
                "m_Day=" + m_Day +
                ", m_Hour=" + m_Hour +
                ", m_ClassID=" + m_ClassID +
                ", m_TeacherID=" + m_TeacherID +
                ", m_SubjectID=" + m_SubjectID +
                '}';
    }
}
