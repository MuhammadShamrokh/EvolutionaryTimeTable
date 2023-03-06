
package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataTransferClasses.MutationData;

import java.util.*;

public class Mutation {

    private String m_Name;

    private Integer m_Tupples;
    private Character m_Char;
    private double m_Probability;
    private eMutation m_eType;
    private Random m_Roller;

    public Mutation(String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        this.setName(i_Name);
        this.setTupples(i_Tupples);
        this.setProbability(i_Probability);
        if(i_Name.toUpperCase().equals("FLIPPING"))
            this.setChar(i_Char);
        else
            m_Char=' ';
        m_Roller=new Random();
    }


    public Mutation(MutationData i_MutationData)
    {
        m_Name=i_MutationData.getName();
        m_Tupples=i_MutationData.getTupples();
        m_Char=i_MutationData.getComponent().charAt(0);
        m_Probability=i_MutationData.getProbability();
        m_eType=eMutation.valueOf(m_Name.toUpperCase());
        m_Roller=new Random();
    }

    public double getProbability() {
        return m_Probability;
    }

    public String getName() {
        return m_Name;
    }

    public eMutation getEType() { return m_eType; }

    public Integer getMaxTupples() {
        return m_Tupples;
    }

    public Character getChar() {
        return m_Char;
    }

    public void setName(String i_Name) {
        if(i_Name.isEmpty())
            throw new RuntimeException("Error: Please pick mutation type");
        if(i_Name.toUpperCase().equals("FLIPPING")|| i_Name.toUpperCase(Locale.ROOT).equals("SIZER")) {
            this.m_Name = i_Name;
            m_eType=eMutation.valueOf(i_Name.toUpperCase());
        }

        else
            throw new RuntimeException("Error: Invalid mutation name.");
    }

    public void setTupples(Integer i_Tupples) {
        m_Tupples = i_Tupples;
    }
    public void setTupples(String i_Tupples) {
        if(i_Tupples.isEmpty())
            throw new RuntimeException("Error: Please enter number of tupples.");
        int tupples;
        try
        {
            tupples=Integer.parseInt(i_Tupples);
        }catch(Exception e)
        {
            throw new RuntimeException("Error: Tupples amount must be a number.");
        }
        if(tupples>0)
            m_Tupples=tupples;
        else
            throw new RuntimeException("Error, number of Tupples must be positive.");


    }
    public void setChar(Character i_Char) {
        m_Char = i_Char;
    }
    public void setChar(String i_Char)
    {
        if(i_Char.isEmpty())
            throw new RuntimeException("Error: Please pick mutation component.");
        if(m_Name.toUpperCase().equals("FLIPPING")) {
            if (i_Char.toUpperCase().equals("C") || i_Char.toUpperCase().equals("T")
                    || i_Char.toUpperCase().equals("S") || i_Char.toUpperCase().equals("D") ||
                    i_Char.toUpperCase().equals("H"))
                m_Char = i_Char.charAt(0);
            else
                throw new RuntimeException("Error: Invalid Mutation Component.");
        }
    }

    public void setProbability(double i_Probability) {
        m_Probability = i_Probability;
    }
    public void setProbability(String i_Probability)
    {
        if (i_Probability.isEmpty())
            throw new RuntimeException("Error: Please enter mutation probability.");
        try
        {
            Double.parseDouble(i_Probability);
        }catch(Exception e)
        {
            throw new RuntimeException("Error: Mutation probability must be a number between 0-1");
        }
        double probability=Double.parseDouble(i_Probability);
        if(probability>=0 && probability<=1)
            m_Probability=probability;
        else
            throw new RuntimeException("Error, Mutation probability must be between 0-1.");

    }

    public void activateMutation(Generation i_Generation, AmountOfObjectsCalc i_AmountOfObj) {
        m_eType.activate(m_Tupples, i_Generation, i_AmountOfObj, m_Char, m_Roller);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mutation mutation = (Mutation) o;
        return m_Name.equals(mutation.m_Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Name);
    }
}
