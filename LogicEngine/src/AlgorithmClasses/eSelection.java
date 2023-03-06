package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Parent;

import java.util.Random;

public enum eSelection {
    TRUNCATION
            {
                @Override
                public void activate(Integer i_Percent,Double i_PTE, Random i_Roller,Generation i_PrevGeneration,Generation i_nextGeneration,Integer i_InitialPopulation
                        ,AmountOfObjectsCalc i_AmountOfObj,Crossover i_Crossover)
                {

                    int size=i_PrevGeneration.getGenerationSize();
                    int sizeAfterSelection= (size*i_Percent)/100; //auto casting to complete value
                    Generation generationAfterSelectingPercent=new Generation();
                    for(int i=0;i<sizeAfterSelection;i++)
                        generationAfterSelectingPercent.addParentToGeneration(i_PrevGeneration.getParentByIndex(i));
                    while(i_nextGeneration.getGenerationSize()<i_InitialPopulation) {
                        i_Crossover.createNewGenerationFromGroupOfParents(generationAfterSelectingPercent, i_nextGeneration, i_AmountOfObj);
                    }
                }

                @Override
                public String toString() {
                    return "Truncation";
                }
            },
    ROULETTEWHEEL
            {
                @Override
                public void activate(Integer i_Percent,Double i_PTE, Random i_Roller,Generation i_PrevGeneration,Generation i_nextGeneration,Integer i_InitialPopulation
                        ,AmountOfObjectsCalc i_AmountOfObj,Crossover i_Crossover) {
                    Parent p1;
                    Parent p2;
                    while(i_nextGeneration.getGenerationSize()<i_InitialPopulation)
                    {
                        p1=activateRouletteWheel(i_PrevGeneration,i_Roller);
                        p2=activateRouletteWheel(i_PrevGeneration,i_Roller);
                        i_Crossover.createTwoChildren(i_nextGeneration,p1,p2,i_AmountOfObj);
                    }
                }

                @Override
                public String toString() {
                    return "RouletteWheel";
                }
            },
    TOURNAMENT {
        @Override
        public void activate(Integer i_Percent, Double i_PTE, Random i_Roller, Generation i_PrevGeneration, Generation i_nextGeneration, Integer i_InitialPopulation, AmountOfObjectsCalc i_AmountOfObj, Crossover i_Crossover) {
            Integer parentOneIndex, parentTwoIndex;
            Parent p1, p2;
            while (i_nextGeneration.getGenerationSize() < i_InitialPopulation) {
                parentOneIndex = i_Roller.nextInt(i_PrevGeneration.getGenerationSize());
                parentTwoIndex = i_Roller.nextInt(i_PrevGeneration.getGenerationSize());
                p1 = activateTournament(i_PrevGeneration, i_PTE, parentOneIndex, parentTwoIndex, i_Roller);
                parentOneIndex = i_Roller.nextInt(i_PrevGeneration.getGenerationSize());
                parentTwoIndex = i_Roller.nextInt(i_PrevGeneration.getGenerationSize());
                p2 = activateTournament(i_PrevGeneration, i_PTE, parentOneIndex, parentTwoIndex, i_Roller);
                i_Crossover.createTwoChildren(i_nextGeneration, p1, p2, i_AmountOfObj);
            }
        }

        @Override
        public String toString() { return "Tournament"; }
    };


    public abstract void activate(Integer i_Percent,Double i_PTE, Random i_Roller, Generation i_PrevGeneration, Generation i_nextGeneration, Integer i_InitialPopulation
            , AmountOfObjectsCalc i_AmountOfObj, Crossover i_Crossover);

    public abstract String toString();
    public Parent activateRouletteWheel(Generation i_Generation,Random i_Roller)
    {
        int scanner=0;
        int index=0;
        int fitness=i_Generation.getSumOfFitness();
        if(fitness==0) //in case all parents with fitness 0
        {
            index=i_Roller.nextInt(i_Generation.getGenerationSize());
            return i_Generation.getParentByIndex(index);
        }
        int rouletteWheelResult=i_Roller.nextInt(fitness);
        if(rouletteWheelResult==0)
            return i_Generation.getParentByIndex(0);
        while(scanner<rouletteWheelResult)
        {
            scanner+=i_Generation.getParentByIndex(index).getFitness();
            index++;
        }
        return i_Generation.getParentByIndex(index-1);
    }
    public Parent activateTournament(Generation i_Generation,Double i_PTE,Integer i_Index1,Integer i_Index2,Random i_Roller)
    {
        double parentChooser=i_Roller.nextDouble();
        Parent first=i_Generation.getParentByIndex(i_Index1);
        Parent second=i_Generation.getParentByIndex(i_Index2);
        Parent retParent=null;

        if(parentChooser>=i_PTE)
        {
            if(first.getFitness()> second.getFitness())
                retParent=first;
            else
                retParent=second;
        }
        else //parent choose smaller than pte
        {
            if(first.getFitness()> second.getFitness())
                retParent=second;
            else
                retParent=first;
        }
        return retParent;

    }

}
