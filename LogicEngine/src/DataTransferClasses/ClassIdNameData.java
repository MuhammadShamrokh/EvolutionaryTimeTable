package DataTransferClasses;

import DataClasses.FileInputDataClasses.Clazz;

public class ClassIdNameData {
    private Integer m_ClassID;
    private String m_ClassName;

    public ClassIdNameData(Clazz i_class)
    {
        m_ClassID= i_class.getId();
        m_ClassName=i_class.getFullName();
    }


    public Integer getTeacherID() {
        return m_ClassID;
    }

    public String getTeacherName() {
        return m_ClassName;
    }
}
