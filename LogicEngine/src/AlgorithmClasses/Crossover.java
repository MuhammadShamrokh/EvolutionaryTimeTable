package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Parent;
import DataTransferClasses.CrossoverData;

import javax.naming.NameNotFoundException;
import java.util.*;

public class Crossover {

    private String m_Name;
    private Integer m_NumOfCuttingPoints;
    private Character m_Char;
    private eCrossover m_eType;
    private Random m_Roller;


    public Crossover(String i_Name,String i_NumOfCuttingPoints,String i_Char)
    {
        this.setName(i_Name);
        this.setNumOfCuttingPoints(i_NumOfCuttingPoints);
        if(i_Name.toUpperCase().equals("ASPECTORIENTED")) {
            this.setChar(i_Char);
        }
        else {
            this.m_Char = ' ';
        }
        m_eType=eCrossover.valueOf(i_Name.toUpperCase());
        m_Roller=new Random();

    }


    public Crossover(CrossoverData i_CrossoverData)
    {
        m_Name=i_CrossoverData.getName();
        m_NumOfCuttingPoints=i_CrossoverData.getNumOfCuttingPoints();
        m_Char=i_CrossoverData.getAspect();
        m_eType=eCrossover.valueOf(m_Name.toUpperCase());
        m_Roller=new Random();
    }

    public CrossoverData getCrossoverData()
    {
        return new CrossoverData(this);
    }

    public eCrossover getConfiguration() {
        return m_eType;
    }

    public String getName() {
        return m_Name;
    }

    public Integer getCuttingPoints() {
        return m_NumOfCuttingPoints;
    }

    public Character getChar() {
        return m_Char;
    }

    public void setName(String i_Name) {
        if(i_Name.isEmpty())
            throw new RuntimeException("Error: Please pick crossover type.");
        if(i_Name.toUpperCase().equals("DAYTIMEORIENTED") || i_Name.toUpperCase().equals("ASPECTORIENTED"))
            this.m_Name = i_Name;
        else
            throw new RuntimeException("Error, Invalid crossover name.");
    }

    public void setNumOfCuttingPoints(String i_NumOfCuttingPoints) {
        if(i_NumOfCuttingPoints.isEmpty())
            throw new RuntimeException("Error: Please enter number of cutting points.");
        try {
            Integer.parseInt(i_NumOfCuttingPoints);
        }catch(Exception e)
        {
            throw new RuntimeException("Error: Cutting points Must be an Integer.");
        }
        int cuttingPoints=Integer.parseInt(i_NumOfCuttingPoints);
        if(cuttingPoints>=1)
            this.m_NumOfCuttingPoints=cuttingPoints;
        else
            throw new RuntimeException("Error: Cutting Points must be positive");

    }

    public void setChar(String i_Char) {
        if(i_Char.isEmpty())
            throw new RuntimeException("Error: Please pick Crossover Component.");
        if(i_Char.toUpperCase().equals("C")||i_Char.toUpperCase().equals("T"))
            this.m_Char = i_Char.charAt(0);
        else
            throw new RuntimeException("Error: Invalid AspectOriented Component.");
    }

    public void createNewGenerationFromGroupOfParents(Generation i_PrevGeneration, Generation i_NextGeneration, AmountOfObjectsCalc i_AmountOfObj){
        Integer generationSize=i_PrevGeneration.getGenerationSize();
        List<Integer> cuttingPoints = rollCuttingPoints(i_AmountOfObj.getMaxAmountOfLessons());////rolling the cutting points
        for (int i = 0; i < generationSize; i += 2) {
            //checking out of range (not always we have pairs)
            if ((i + 1) == generationSize)
                i--;
            m_eType.activate(i_PrevGeneration.getParentByIndex(i), i_PrevGeneration.getParentByIndex(i + 1)
                    , i_AmountOfObj,m_Char, cuttingPoints, i_NextGeneration);
        }

    }

    public void createTwoChildren(Generation i_NextGeneration,Parent i_P1,Parent i_P2,AmountOfObjectsCalc i_AmountOfObj)
    {
        List<Integer> cuttingPoints = rollCuttingPoints(i_AmountOfObj.getMaxAmountOfLessons());////rolling the cutting
        m_eType.activate(i_P1,i_P2,i_AmountOfObj,m_Char,cuttingPoints,i_NextGeneration);
    }


    private List<Integer> rollCuttingPoints(Integer i_Max)
    {
        List<Integer> retList=new ArrayList<>(m_NumOfCuttingPoints);
        int index;
        for(int i = 1; i<= m_NumOfCuttingPoints; i++) {
            do {
                index = m_Roller.nextInt(i_Max - 1) + 1; //1 -> (maxAmount-1)
            } while (retList.contains(index));
            retList.add(index);
        }
        return retList;
    }

}
