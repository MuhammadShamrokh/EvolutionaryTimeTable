package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataTransferClasses.MutationData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mutations {

    private List<Mutation> m_MutationsList;
    private Random m_Roller;

    public Mutations()
    {
        m_MutationsList=new ArrayList<>();
        m_Roller=new Random();
    }
    public Mutations(List<MutationData> i_MutationDataList)
    {
        m_MutationsList=new ArrayList<>();
        for(MutationData mutation:i_MutationDataList)
        {
            m_MutationsList.add(new Mutation(mutation));
        }
        m_Roller=new Random();
    }

    public List<Mutation> getMutationsList(){return m_MutationsList;}
    public List<MutationData> getMutationsDataList()
    {
        List<MutationData> retLst=new ArrayList<>();
        m_MutationsList.forEach(mutation->retLst.add(new MutationData(mutation)));
        return retLst;
    }


    public Mutation getMutationByIndex(Integer i_Index){
        return m_MutationsList.get(i_Index);
    }
    public MutationData getMutationDataByIndex(Integer i_Index)
    {
        return new MutationData(m_MutationsList.get(i_Index.intValue()));
    }
    public Mutation getMutationByString(String i_String)
    {
        Mutation retMutation=null;
        for(Mutation m:m_MutationsList)
        {
            String mutationString;
            mutationString=m.getEType().toString(m.getMaxTupples(),m.getChar());
            if(mutationString.equals(i_String)) {
                retMutation = m;
                break;
            }
        }
        return retMutation;
    }

    public void scanAndActivateMutations(Generation i_Generation, AmountOfObjectsCalc i_Amounts)
    {
        for(Mutation m:m_MutationsList)
        {
            double chooseIFToActivate=m_Roller.nextDouble();
            if(chooseIFToActivate<=m.getProbability())
                m.activateMutation(i_Generation,i_Amounts);
        }
    }

    public void createAndAddMutationToList(String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        Mutation mutation=new Mutation(i_Name,i_Tupples,i_Char,i_Probability);
        m_MutationsList.add(mutation);
    }

    public void updateMutation(Integer i_Index,String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        Mutation mutation=m_MutationsList.get(i_Index);
        mutation.setName(i_Name);
        mutation.setTupples(i_Tupples);
        if(i_Name.toUpperCase().equals("FLIPPING"))
            mutation.setChar(i_Char);
        else //sizer, no need char so enter empty char
        {
            char ch=' ';
            mutation.setChar(ch);
        }
        mutation.setProbability(i_Probability);
    }

    public void addMutationToList(Mutation i_Mutation){m_MutationsList.add(i_Mutation);}


    public void deleteMutationByIndex(Integer i_MutationIndex)
    {
        m_MutationsList.remove(i_MutationIndex.intValue());
    }


}
