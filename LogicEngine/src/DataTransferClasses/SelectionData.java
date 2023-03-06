package DataTransferClasses;

import AlgorithmClasses.Selection;

import java.util.List;

public class SelectionData {
    private String m_Type;
    private Integer m_Percent;
    private double m_PTE;
    private Integer m_Elitism;

    public SelectionData(Selection i_Selection)
    {
        m_Type=i_Selection.getType();
        m_Percent=i_Selection.getPercent();
        m_Elitism=i_Selection.getElitism();
        m_PTE=i_Selection.getPTE();
    }

    public String getType() {
        return m_Type;
    }

    public Integer getPercent() {
        return m_Percent;
    }

    public Integer getElitism() {
        return m_Elitism;
    }

    public double getPTE(){return m_PTE;}

    public void setType(String i_Type) {
        this.m_Type = i_Type;
    }

    public void setPercent(Integer i_Percent) {
        this.m_Percent = i_Percent;
    }

    public void setPTE(double i_PTE) {
        this.m_PTE = i_PTE;
    }

    public void setElitism(Integer i_Elitism) {
        this.m_Elitism = i_Elitism;
    }

    @Override
    public String toString() {
        String outputString="";
        outputString+="Name: "+m_Type;
        outputString+=" Elitism: "+m_Elitism;
        if(m_Type.toUpperCase().equals("TRUNCATION"))
            outputString+=" Top percent: "+m_Percent;
        if(m_Type.toUpperCase().equals("TOURNAMENT"))
            outputString+=" PTE: "+m_PTE;
        return outputString;
    }
}
