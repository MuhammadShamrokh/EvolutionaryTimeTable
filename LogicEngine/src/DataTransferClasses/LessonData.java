package DataTransferClasses;

import DataClasses.AlgorithmData.Lesson;

public class LessonData {
    private Integer m_Day;
    private Integer m_Hour;
    private Integer m_ClassID;
    private Integer m_TeacherID;
    private Integer m_SubjectID;

    public LessonData(Lesson i_Lesson)
    {
        m_Day= i_Lesson.getDay();
        m_Hour=i_Lesson.getHour();
        m_ClassID= i_Lesson.getClassID();
        m_TeacherID= i_Lesson.getTeacherID();
        m_SubjectID= i_Lesson.getSubjectID();
    }

    public Integer getDay() {
        return m_Day;
    }

    public Integer getHour() {
        return m_Hour;
    }

    public Integer getClassID() {
        return m_ClassID;
    }

    public Integer getTeacherID() {
        return m_TeacherID;
    }

    public Integer getSubjectID() {
        return m_SubjectID;
    }
}
