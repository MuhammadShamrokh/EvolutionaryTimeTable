package Application;

import DataTransferClasses.DataPrinter;
import DataTransferClasses.MutationData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

public class ArgumentsFiller {
    private DataPrinter m_DataPrinter;

    public ArgumentsFiller(DataPrinter i_DataPrinter)
    {
        m_DataPrinter=i_DataPrinter;
    }

    public void setDataPrinter(DataPrinter i_DataPrinter) {
        m_DataPrinter = i_DataPrinter;
    }

    //Fitness filler
    public void setFitnessCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().clear();
        for(int i=1;i<=100;i++)
        {
            i_ComboBox.getItems().add(i);
        }
    }

    //Show values filler
    public void setShowValuesCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().clear();
        for(eResultsValues value:eResultsValues.values())
        {
            i_ComboBox.getItems().add(value.toString());
        }
    }

    //Selection fillers
    public void setSelectionTypeCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().clear();
        for(String name:m_DataPrinter.getSelectionNamesList())
        {
            i_ComboBox.getItems().add(name);
        }
        i_ComboBox.getSelectionModel().select(m_DataPrinter.getSelectionData().getType());
    }
    public void setSelectionTopPercentCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().clear();
        for(int i=1;i<=100;i++)
        {
            i_ComboBox.getItems().add(i);
        }
        if(m_DataPrinter.getSelectionData().getType().toUpperCase().equals("TRUNCATION"))
            i_ComboBox.getSelectionModel().select(m_DataPrinter.getSelectionData().getPercent());
    }
    public void updateSelectionTopPercentCombo(ComboBox i_ComboBox)
    {
        if(m_DataPrinter.getSelectionData().getPercent()!=0)
            i_ComboBox.getSelectionModel().select(m_DataPrinter.getSelectionData().getPercent());
        else
            i_ComboBox.getSelectionModel().clearSelection();
    }
    public void setSelectionElitismSliderMax(Slider i_Slider)
    {
        i_Slider.setMax(m_DataPrinter.getInitialPopulation()-1);
        i_Slider.setValue(m_DataPrinter.getSelectionData().getElitism());
    }
    public void updateSelectionElitismSlider(Slider i_Slider)
    {
        i_Slider.setValue(m_DataPrinter.getSelectionData().getElitism());
    }
    public void setSelectionPTECombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().clear();
        for(int i=0;i<=10;i++)
        {
            i_ComboBox.getItems().add(String.valueOf((double)i/(double)10));
        }
        if(m_DataPrinter.getSelectionData().getType().toUpperCase().equals("TOURNAMENT")) {
            i_ComboBox.getSelectionModel().select(m_DataPrinter.getSelectionData().getPTE());
        }
    }
    public void updateSelectionPTECombo(ComboBox i_ComboBox)
    {
        if(m_DataPrinter.getSelectionData().getType().toUpperCase().equals("TOURNAMENT")) {
            i_ComboBox.getSelectionModel().select(m_DataPrinter.getSelectionData().getPTE());
        }
    }

    //Crossover fillers
    public void setCrossoverTypeCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().clear();
        for(String name:m_DataPrinter.getCrossoverNamesList())
        {
            i_ComboBox.getItems().add(name);
        }
        i_ComboBox.getSelectionModel().select(m_DataPrinter.getCrossoverData().getName());
    }
    public void setCrossoverAspectCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().clear();
        i_ComboBox.getItems().add("Teacher");
        i_ComboBox.getItems().add("Class");
        if(m_DataPrinter.getCrossoverData().getName().toUpperCase().equals("ASPECTORIENTED")) {
            Character aspect = m_DataPrinter.getCrossoverData().getAspect();
            switch (aspect) {
                case 'T':
                    i_ComboBox.getSelectionModel().select("Teacher");
                    break;
                case 'C':
                    i_ComboBox.getSelectionModel().select("Class");
                    break;
            }
        }
    }
    public void updateCrossoverAspectCombo(ComboBox i_ComboBox)
    {
        if(m_DataPrinter.getCrossoverData().getName().toUpperCase().equals("ASPECTORIENTED")) {
            Character aspect = m_DataPrinter.getCrossoverData().getAspect();
            switch (aspect) {
                case 'T':
                    i_ComboBox.getSelectionModel().select("Teacher");
                    break;
                case 'C':
                    i_ComboBox.getSelectionModel().select("Class");
                    break;
            }
        }
    }
    public void setCrossoverCuttingPoints(TextField i_TextField)
    {
        i_TextField.setText(m_DataPrinter.getCrossoverData().getNumOfCuttingPoints().toString());
    }
    public void updateCrossoverCuttingPoints(TextField i_TextField)
    {
        i_TextField.setText(m_DataPrinter.getCrossoverData().getNumOfCuttingPoints().toString());
    }

    //Mutation fillers
    public void setMutationTypeCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().clear();
        List<MutationData> mutationsDataList = m_DataPrinter.getMutationsDataList();
        for(int i=1;i<= mutationsDataList.size();i++)
        {
            i_ComboBox.getItems().add(i+". "+mutationsDataList.get(i-1).getName());
        }
        i_ComboBox.getSelectionModel().select(0);
    }
    public void setMutationProbabilityCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().clear();
        for(int i=0;i<=10;i++)
        {
            i_ComboBox.getItems().add(String.valueOf((double)i/(double)10));
        }
        i_ComboBox.getSelectionModel().select(m_DataPrinter.getMutationsDataList().get(0).getProbability());
    }
    public void updateMutationProbabilityCombo(ComboBox i_ComboBox,Integer i_MutationIndex)
    {
        i_ComboBox.getSelectionModel().select(m_DataPrinter.getMutationsDataList().get(i_MutationIndex).getProbability());
    }
    public void setMutationTupples(TextField i_TextField)
    {
        i_TextField.setText(m_DataPrinter.getMutationsDataList().get(0).getTupples().toString());
    }
    public void updateMutationTupples(TextField i_TextField,Integer i_MutationIndex)
    {
        i_TextField.setText(m_DataPrinter.getMutationsDataList().get(i_MutationIndex).getTupples().toString());
    }
    public void setMutationComponentCombo(ComboBox i_ComboBox)
    {
        i_ComboBox.getItems().clear();
        i_ComboBox.getItems().add("Teacher");
        i_ComboBox.getItems().add("Class");
        i_ComboBox.getItems().add("Subject");
        i_ComboBox.getItems().add("Day");
        i_ComboBox.getItems().add("Hour");

        if(m_DataPrinter.getMutationsDataList().get(0).getName().toUpperCase().equals("FLIPPING"))
        {
            Character component=m_DataPrinter.getMutationsDataList().get(0).getComponent().charAt(0);
            switch (component)
            {
                case 'T':
                    i_ComboBox.getSelectionModel().select("Teacher");
                    break;
                case 'C':
                    i_ComboBox.getSelectionModel().select("Class");
                    break;
                case 'S':
                    i_ComboBox.getSelectionModel().select("Subject");
                    break;
                case 'D':
                    i_ComboBox.getSelectionModel().select("Day");
                    break;
                case 'H':
                    i_ComboBox.getSelectionModel().select("Hour");
                    break;
            }
        }
    }
    public void updateMutationComponentCombo(ComboBox i_ComboBox,Integer i_MutationIndex)
    {
        Character component=m_DataPrinter.getMutationsDataList().get(i_MutationIndex).getComponent().charAt(0);
        switch (component)
        {
            case 'T':
                i_ComboBox.getSelectionModel().select("Teacher");
                break;
            case 'C':
                i_ComboBox.getSelectionModel().select("Class");
                break;
            case 'S':
                i_ComboBox.getSelectionModel().select("Subject");
                break;
            case 'D':
                i_ComboBox.getSelectionModel().select("Day");
                break;
            case 'H':
                i_ComboBox.getSelectionModel().select("Hour");
                break;
        }
    }
}
