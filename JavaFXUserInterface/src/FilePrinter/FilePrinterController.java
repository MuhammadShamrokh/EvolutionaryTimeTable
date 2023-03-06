package FilePrinter;

import DataTransferClasses.DataPrinter;
import DataTransferClasses.MutationData;
import DataTransferClasses.StudyData;
import DataTransferClasses.SubjectData;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.util.Map;
import java.util.Set;

public class FilePrinterController {

    @FXML
    private GridPane mainGridPane;

    @FXML
    private TextArea valuesTextArea;

    @FXML
    private ListView<String> valuesListView;

    private DataPrinter m_DataPrinter;

    @FXML
    private void initialize()
    {
        valuesListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> printFileData(newValue)));
    }

    public GridPane getComponent()
    {
        return mainGridPane;
    }

    public void setView(DataPrinter i_DataPrinter)
    {
        m_DataPrinter=i_DataPrinter;
        setListViewValues();
    }

    private void setListViewValues()
    {
        valuesListView.getItems().clear();
        valuesListView.getItems().add("Subjects");
        valuesListView.getItems().add("Teachers");
        valuesListView.getItems().add("Classes");
        valuesListView.getItems().add("Rules");
        valuesListView.getItems().add("Algorithm Preferences");
    }

    private void printFileData(String i_PrintValue)
    {
        if(i_PrintValue!=null) {
            switch (i_PrintValue) {
                case "Subjects":
                    showSubjectsData();
                    break;
                case "Teachers":
                    showTeachersData();
                    break;
                case "Classes":
                    showClassesData();
                    break;
                case "Rules":
                    showRulesData();
                    break;
                case "Algorithm Preferences":
                    showAlgorithmPreferencesData();
                    break;
            }
        }
    }

    private void showSubjectsData()
    {
        String outputString="The subjects are:";
        for(SubjectData subj:m_DataPrinter.getSubjectsSet())
        {
            outputString+=System.lineSeparator()+"ID: "+ subj.getSubjectID() + " | Name: " + subj.getSubjectName();
        }
        valuesTextArea.setText(outputString);
    }

    private void showTeachersData()
    {
        String outputString="The teachers are:";
        for(Integer teacherID: m_DataPrinter.getTeacherID2SubjectsMap().keySet())
        {
            Set<SubjectData> teacherSubjects = m_DataPrinter.getTeacherID2SubjectsMap().get(teacherID);
            outputString+=System.lineSeparator()+"Teacher ID: "+teacherID+", The subjects he teaches are:";
            for(SubjectData subj:teacherSubjects)
            {
                outputString+=System.lineSeparator()+"ID: "+subj.getSubjectID() + " | Name: " + subj.getSubjectName();
            }
            outputString+=System.lineSeparator();
        }
        valuesTextArea.setText(outputString);
    }

    private void showClassesData()
    {
        String outputString="The classes are:";
        for(Integer classID: m_DataPrinter.getClassID2SubjectsMap().keySet())
        {
            Set<StudyData> classSubjects = m_DataPrinter.getClassID2SubjectsMap().get(classID);
            outputString+=System.lineSeparator()+"Class ID: "+classID+", The subjects taught in this class are:";
            for(StudyData subj:classSubjects)
            {
                outputString+=System.lineSeparator()+"Subject ID: "+subj.getSubjectID()+" | Subject name: "+subj.getSubjectName()
                        +" | Hours: "+ subj.getReqHours();
            }
            outputString+=System.lineSeparator();
        }
        valuesTextArea.setText(outputString);
    }

    private void showRulesData()
    {
        String outputString="The rules are:";
        for(Map.Entry entry: m_DataPrinter.getRulesNames2TypeMap().entrySet())
        {
            outputString+=System.lineSeparator()+"Name: "+entry.getKey()+" | Type: "+entry.getValue();
        }
        valuesTextArea.setText(outputString);
    }

    private void showAlgorithmPreferencesData()
    {
        String outputString="Algorithm Preferences:";
        outputString+=System.lineSeparator()+"Initial population: "+m_DataPrinter.getInitialPopulation();
        outputString+=System.lineSeparator();
        outputString+=System.lineSeparator()+"Selection:"+System.lineSeparator()+m_DataPrinter.getSelectionData().toString();
        outputString+=System.lineSeparator();
        outputString+=System.lineSeparator()+"Crossover:"+System.lineSeparator()+m_DataPrinter.getCrossoverData().toString();
        outputString+=System.lineSeparator();
        outputString+=System.lineSeparator()+"Mutations:";
        for(MutationData mutation:m_DataPrinter.getMutationsDataList())
        {
            outputString+=System.lineSeparator()+"Name: "+mutation.getName()+" Probability: "+mutation.getProbability();
            if(mutation.getName().toUpperCase().equals("FLIPPING"))
                outputString+=" Component: "+mutation.getComponent();
        }
        valuesTextArea.setText(outputString);
    }
}
