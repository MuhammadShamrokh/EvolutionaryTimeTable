package DataTransferClasses;

import DataClasses.FileInputDataClasses.Teacher;

public class TeacherIdNameData {
    private Integer m_TeacherID;
    private String m_TeacherName;

    public TeacherIdNameData(Teacher i_Teacher)
    {
        m_TeacherID= i_Teacher.getId();
        m_TeacherName=i_Teacher.getFullName();
    }


    public Integer getTeacherID() {
        return m_TeacherID;
    }

    public String getTeacherName() {
        return m_TeacherName;
    }
}
