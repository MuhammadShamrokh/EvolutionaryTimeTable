package DataTransferClasses;

import AlgorithmClasses.Mutation;
import AlgorithmClasses.eMutation;

import java.util.List;

public class MutationData {
    private double m_Probability;
    private String m_Name;
    private Integer m_Tupples;
    private String m_Component;

    public MutationData(Mutation i_Mutation) {
        m_Name = i_Mutation.getName();
        m_Probability = i_Mutation.getProbability();
        m_Tupples = i_Mutation.getMaxTupples();
        if (m_Name.toUpperCase().equals("FLIPPING"))
            setComponent(i_Mutation.getChar());
        else
            m_Component = " ";
    }


    public double getProbability() {
        return m_Probability;
    }

    public String getName() {
        return m_Name;
    }

    public Integer getTupples() {
        return m_Tupples;
    }

    public String getComponent() {
        return m_Component;
    }

    public void setProbability(double i_Probability) {
        m_Probability = i_Probability;
    }

    public void setTupples(Integer i_Tupples) {
        m_Tupples = i_Tupples;
    }

    public void setComponent(Character i_Component) {
        switch(i_Component)
        {
            case 'H':
                m_Component="Hour";
                break;
            case 'D':
                m_Component="Day";
                break;
            case 'C':
                m_Component="Class";
                break;
            case 'T':
                m_Component="Teacher";
                break;
            case 'S':
                m_Component="Subject";
                break;
            default:
                throw new RuntimeException("Error, Invalid mutation component!");
        }
    }

    @Override
    public String toString() {
        eMutation eType=eMutation.valueOf(m_Name.toUpperCase());
        return eType.toString(m_Tupples,m_Component.charAt(0));
    }
}
