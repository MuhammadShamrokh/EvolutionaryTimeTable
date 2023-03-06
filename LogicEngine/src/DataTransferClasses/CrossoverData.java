package DataTransferClasses;

import AlgorithmClasses.Crossover;

public class CrossoverData {
    private String m_Name;
    private Integer m_NumOfCuttingPoints;
    private Character m_Aspect;

    public CrossoverData(Crossover i_Crossover) {
        m_Name = i_Crossover.getName();
        m_NumOfCuttingPoints = i_Crossover.getCuttingPoints();
        m_Aspect = i_Crossover.getChar();
    }


    public String getName() {
        return m_Name;
    }

    public Integer getNumOfCuttingPoints() {
        return m_NumOfCuttingPoints;
    }

    public Character getAspect() {
        return m_Aspect;
    }

    public void setName(String i_Name) {
        this.m_Name = i_Name;
    }

    public void setNumOfCuttingPoints(Integer i_NumOfCuttingPoints) {
        this.m_NumOfCuttingPoints = i_NumOfCuttingPoints;
    }

    public void setAspect(Character i_Aspect) {
        this.m_Aspect = i_Aspect;
    }

    @Override
    public String toString() {
        String outputString="";
        outputString+="Name: "+m_Name;
        outputString+=" Cutting points: "+m_NumOfCuttingPoints;
        if(m_Name.toUpperCase().equals("ASPECTORIENTED"))
            outputString+=" Aspect: "+m_Aspect;
        return outputString;
    }
}