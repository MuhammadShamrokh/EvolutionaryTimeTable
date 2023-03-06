package DataClasses.FileInputDataClasses;

import ParsedClasses.ETTClass;

import java.util.List;
import java.util.Objects;

public class Clazz {
    private final Integer m_Id;
    private String m_FullName;
    private Requirements m_Requirements;

    public Clazz(ETTClass i_ETTClass)
    {
        m_Id=i_ETTClass.getId();
        m_Requirements=new Requirements(i_ETTClass.getETTRequirements());
        m_FullName=i_ETTClass.getETTName();
    }

    public Clazz(Clazz i_Clazz)
    {
        m_Id=i_Clazz.getId();
        m_Requirements=new Requirements(i_Clazz.getRequirements());
        m_FullName=i_Clazz.getFullName();
    }



    @Override
    public String toString() {
        return "Clazz{" +
                "m_Id=" + m_Id +
                ", m_FullName=" + m_FullName +
                ", m_Requirements=" + m_Requirements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return m_Id.equals(clazz.m_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Id);
    }

    public Integer getId() {
        return m_Id;
    }

    public String getFullName() {
        return m_FullName;
    }

    public Requirements getRequirements() {
        return m_Requirements;
    }
}
