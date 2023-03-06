package Application;

import DataTransferClasses.CrossoverData;
import DataTransferClasses.MutationData;
import Tasks.LoadFileTask;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

public class ValuesChecker {

    public Boolean checkAtLeastOneStopCondition(CheckBox i_GenerationsCheck, CheckBox i_FitnessCheck, CheckBox i_TimeCheck)
    {
        Boolean isGood=true;
        if(!(i_GenerationsCheck.isSelected()||i_FitnessCheck.isSelected()||i_TimeCheck.isSelected()))
        {
            isGood=false;
        }
        return isGood;
    }

    public Boolean checkNumOfGenerations(TextField i_NumOfGen)
    {
        Boolean isGood=true;
        if(checkIfTextFieldInteger(i_NumOfGen)&&Integer.parseInt(i_NumOfGen.getText())>=100)
        {
            changeToGoodBorder(i_NumOfGen);
        }
        else
        {
            changeToErrorBorder(i_NumOfGen);
            isGood=false;
        }

        return isGood;
    }

    public Boolean checkTime(TextField i_Time)
    {
        Boolean isGood=true;
        if(checkIfTextFieldInteger(i_Time))
        {
            changeToGoodBorder(i_Time);
        }
        else
        {
            changeToErrorBorder(i_Time);
            isGood=false;
        }

        return isGood;
    }

    public Boolean checkCuttingPoints(TextField i_CuttingPoints, Integer i_MaxLessons)
    {
        Boolean isGood=true;
        if(checkIfTextFieldInteger(i_CuttingPoints)&&Integer.parseInt(i_CuttingPoints.getText())<i_MaxLessons-1)
        {
            changeToGoodBorder(i_CuttingPoints);
        }
        else
        {
            changeToErrorBorder(i_CuttingPoints);
            isGood=false;
        }

        return isGood;
    }

    public Boolean checkTupples(TextField i_Tupples)
    {
        Boolean isGood=true;
        if(checkIfTextFieldInteger(i_Tupples)&&Integer.parseInt(i_Tupples.getText())>0)
        {
            changeToGoodBorder(i_Tupples);
        }
        else
        {
            changeToErrorBorder(i_Tupples);
            isGood=false;
        }

        return isGood;
    }

    public Boolean checkShowEvery(TextField i_ShowEvery,CheckBox i_GenCheck, TextField i_GenNum)
    {
        Boolean isGood=true;
        if(checkIfTextFieldInteger(i_ShowEvery)&&checkShowEveryBelowGenerations(i_ShowEvery,i_GenCheck,i_GenNum))
        {
            changeToGoodBorder(i_ShowEvery);
        }
        else
        {
            changeToErrorBorder(i_ShowEvery);
            isGood=false;
        }

        return isGood;
    }

    private Boolean checkShowEveryBelowGenerations(TextField i_ShowEvery, CheckBox i_GenCheck, TextField i_GenNum)
    {
        Boolean isGood=true;
        if(i_GenCheck.isSelected()&&checkNumOfGenerations(i_GenNum))
        {
            if(Integer.parseInt(i_ShowEvery.getText())>Integer.parseInt(i_GenNum.getText()))
                isGood=false;
        }
        return isGood;
    }

    public void checkMutationArguments(MutationData i_MutationsData, ComboBox i_Probability, TextField i_Tupples, ComboBox i_Component)
    {
        String mutationName=i_MutationsData.getName();
        if(i_Probability.getValue()==null||i_Tupples.getText().isEmpty()||
                (mutationName.toUpperCase().equals("FLIPPING")&&i_Component.getValue()==null))
        {
            throw new RuntimeException("There are empty mutation arguments!");
        }
        if(!checkTupples(i_Tupples))
            throw new RuntimeException("Wrong tupples value. must be a positive number");
    }

    public void checkCrossoverArguments(ComboBox i_CrossoverType, TextField i_CuttingPoints, ComboBox i_Aspect, Integer i_MaxLessons)
    {
        String crossoverName=i_CrossoverType.getValue().toString();
        if(i_CuttingPoints.getText().isEmpty()||
                (crossoverName.toUpperCase().equals("ASPECTORIENTED")&&i_Aspect.getValue()==null))
        {
            throw new RuntimeException("There are empty crossover arguments!");
        }
        if(!checkCuttingPoints(i_CuttingPoints,i_MaxLessons))
            throw new RuntimeException("Wrong cutting points value. Should be 1-"+(i_MaxLessons-1));
    }

    public void checkSelectionArguments(ComboBox i_SelectionType, ComboBox i_Percent, ComboBox i_PTE)
    {
        String selectionName=i_SelectionType.getValue().toString();
        if((selectionName.toUpperCase().equals("TRUNCATION")&&i_Percent.getValue()==null)||
                (selectionName.toUpperCase().equals("TOURNAMENT")&&i_PTE.getValue()==null))
        {
            throw new RuntimeException("There are empty selection arguments!");
        }
    }

    public void checkStopConditionsArguments(CheckBox i_GenerationsCheck, TextField i_GenerationsTF,CheckBox i_FitnessCheck, ComboBox i_FitnessCombo,
                                             CheckBox i_TimeCheck, TextField i_TimeTF,TextField i_ShowEvery)
    {
        if(checkAtLeastOneStopCondition(i_GenerationsCheck,i_FitnessCheck,i_TimeCheck)) {
            if (i_GenerationsCheck.isSelected() && !checkNumOfGenerations(i_GenerationsTF)) {
                throw new RuntimeException("Wrong generations stop condition argument!");
            }
            if (i_FitnessCheck.isSelected() && i_FitnessCombo.getValue() == null) {
                throw new RuntimeException("Empty fitness stop condition argument!");
            }
            if (i_TimeCheck.isSelected() && !checkTime(i_TimeTF)) {
                throw new RuntimeException("Wrong time stop condition argument!");
            }
            if (!checkShowEvery(i_ShowEvery,i_GenerationsCheck,i_GenerationsTF)) {
                throw new RuntimeException("Wrong show every argument!");
            }
        }
        else
        {
            throw new RuntimeException("At least one stop condition should be selected!");
        }
    }

    private Boolean checkIfTextFieldInteger(TextField i_TextField)
    {
        Boolean isGood=true;
        Integer intValue;
        try {
            intValue=Integer.parseInt(i_TextField.getText());
            if(intValue<1)
                isGood=false;
        }
        catch (Exception e)
        {
            isGood=false;
        }
        return isGood;
    }

    private void changeToErrorBorder(TextField i_TextField)
    {
        i_TextField.setStyle("-fx-border-color: red; -fx-border-width: 3");
    }

    private void changeToGoodBorder(TextField i_TextField)
    {
        i_TextField.setStyle("-fx-border-color: green; -fx-border-width: 3");
    }

    public void changeToDefaultBorder(TextField i_TextField)
    {
        i_TextField.setStyle("-fx-border-color: null; -fx-border-width: null");
    }
}
