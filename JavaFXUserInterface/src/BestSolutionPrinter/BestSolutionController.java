package BestSolutionPrinter;

import Application.ApplicationController;
import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataTransferClasses.BestSolutionsData;
import DataTransferClasses.LessonData;
import DataTransferClasses.RuleData;
import RulesPrinter.RulesPrinter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BestSolutionController {

    private BestSolutionsData m_BestSolutionData;
    private AmountOfObjectsCalc m_MaxAmounts;
    private ScrollPane m_DynamicRoot;
    private ScrollPane m_RulesRoot;
    private ApplicationController m_AppController;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private ListView<String> valuesListView;

    @FXML
    private void initialize()
    {
        //valuesListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> printBestSolution(newValue)));
    }

    public void setView(ApplicationController i_AppController,ScrollPane i_DynamicRoot, ScrollPane i_RulesRoot, BestSolutionsData i_BestSolutionData, AmountOfObjectsCalc i_MaxAmounts)
    {
        m_DynamicRoot=i_DynamicRoot;
        m_RulesRoot=i_RulesRoot;
        m_BestSolutionData=i_BestSolutionData;
        m_MaxAmounts=i_MaxAmounts;
        m_AppController=i_AppController;
    }

    public void printByRaw()
    {
        valuesListView.getItems().clear();
        FlowPane flowPane=new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(10,10,10,10));
        m_BestSolutionData.getLessonsDataList().sort(Comparator
                .comparing(LessonData::getDay)
                .thenComparing(LessonData::getHour)
                .thenComparing(LessonData::getClassID)
                .thenComparing(LessonData::getTeacherID));
        for(LessonData lesson:m_BestSolutionData.getLessonsDataList())
        {
            flowPane.getChildren().add(createNewLessonComponent(lesson,m_AppController.isAnimated()));
        }
        m_DynamicRoot.setContent(flowPane);
        List<RuleData> rulesDataList = m_BestSolutionData.getRulesDataList();
        RulesPrinter rulesPrinter=new RulesPrinter(rulesDataList,m_RulesRoot);
        rulesPrinter.showRules(m_AppController.isAnimated());
    }

    public void printByTeacher()
    {
        setValuesListViewTeacher();
    }

    public void printByClass()
    {
        setValuesListViewClass();
    }

    private void setValuesListViewClass() {
        valuesListView.getItems().clear();
        Integer amountOfClasses = m_MaxAmounts.getAmountOfClasses();
        for(int i=1;i<=amountOfClasses;i++)
        {
            valuesListView.getItems().add(i+". "+m_AppController.getEngine().getClassNameById(i));
        }
        valuesListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> printSelectedClass(newValue)));
        valuesListView.getSelectionModel().selectFirst();
    }



    private StackPane createNewLessonComponent(LessonData i_LessonData, Boolean i_IsAnimated)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource("LessonComponent.fxml");
        fxmlLoader.setLocation(url);
        StackPane root = null;
        try {
            root = fxmlLoader.load(url.openStream());
            LessonController controller = (LessonController) fxmlLoader.getController();
            controller.setDayText(i_LessonData.getDay().toString());
            controller.setHourText(i_LessonData.getHour().toString());
            controller.setClassText(m_AppController.getEngine().getClassNameById(i_LessonData.getClassID()));
            controller.setTeacherText(m_AppController.getEngine().getTeacherNameById(i_LessonData.getTeacherID()));
            controller.setSubjectText(m_AppController.getEngine().getSubjectNameById(i_LessonData.getSubjectID()));
            if(i_IsAnimated)
                controller.startStrokeTransition();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setValuesListViewTeacher()
    {
        valuesListView.getItems().clear();
        Integer amountOfTeachers = m_MaxAmounts.getAmountOfTeachers();
        for(int i=1;i<=amountOfTeachers;i++)
        {
            valuesListView.getItems().add(i+". "+m_AppController.getEngine().getTeacherNameById(i));
        }
        valuesListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> printSelectedTeacher(newValue)));
        valuesListView.getSelectionModel().selectFirst();
    }

    private void printSelectedClass(String i_Choice) {
        Integer classID=Integer.parseInt(i_Choice.substring(0,1));
        GridPane gridPane=initialGridPane();

        List<LessonData> lessonsOfClassList = m_BestSolutionData.getLessonsDataList().stream().filter(lesson -> lesson.getClassID().equals(classID)).collect(Collectors.toList());
        for(int h=0;h<m_MaxAmounts.getAmountOfHours();h++)
        {
            for (int d=0;d<m_MaxAmounts.getAmountOfDays();d++)
            {
                Integer day=d+1;
                Integer hour=h+1;
                List<LessonData> dayHourLessons = lessonsOfClassList.stream().filter(
                        lesson -> lesson.getDay().equals(day) && lesson.getHour().equals(hour)).collect(Collectors.toList());
                if(!dayHourLessons.isEmpty()) {
                    StackPane newLessonComponent = createNewLessonComponent(dayHourLessons.get(0),m_AppController.isAnimated());
                    gridPane.add(newLessonComponent,day,hour);
                }
            }
        }

        mainGridPane.getChildren().removeIf((node1 -> node1 instanceof GridPane));
        mainGridPane.add(gridPane,1,0);
        m_DynamicRoot.setContent(mainGridPane);
        List<RuleData> rulesDataList = m_BestSolutionData.getRulesDataList();
        RulesPrinter rulesPrinter=new RulesPrinter(rulesDataList,m_RulesRoot);
        rulesPrinter.showRules(m_AppController.isAnimated());
    }

    private void printSelectedTeacher(String i_Choice)
    {
        Integer teacherID=Integer.parseInt(i_Choice.substring(0,1));
        GridPane gridPane=initialGridPane();

        List<LessonData> lessonsOfTeacherList = m_BestSolutionData.getLessonsDataList().stream().filter(lesson -> lesson.getTeacherID().equals(teacherID)).collect(Collectors.toList());
        for(int h=0;h<m_MaxAmounts.getAmountOfHours();h++)
        {
            for (int d=0;d<m_MaxAmounts.getAmountOfDays();d++)
            {
                Integer day=d+1;
                Integer hour=h+1;
                List<LessonData> dayHourLessons = lessonsOfTeacherList.stream().filter(
                        lesson -> lesson.getDay().equals(day) && lesson.getHour().equals(hour)).collect(Collectors.toList());
                if(!dayHourLessons.isEmpty()) {
                    StackPane newLessonComponent = createNewLessonComponent(dayHourLessons.get(0),m_AppController.isAnimated());
                    gridPane.add(newLessonComponent,day,hour);
                }
            }
        }
        mainGridPane.getChildren().removeIf((node1 -> node1 instanceof GridPane));
        mainGridPane.add(gridPane,1,0);
        m_DynamicRoot.setContent(mainGridPane);
        List<RuleData> rulesDataList = m_BestSolutionData.getRulesDataList();
        RulesPrinter rulesPrinter=new RulesPrinter(rulesDataList,m_RulesRoot);
        rulesPrinter.showRules(m_AppController.isAnimated());
    }

    private GridPane initialGridPane()
    {
        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getStyleClass().add("grid-pane");
        gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);

        for(int h=0;h<m_MaxAmounts.getAmountOfHours()+1;h++)
        {
            gridPane.addRow(h);
            Label label=new Label();
            if(h==0)
            {
                label.setText("Hour/Day");
                gridPane.add(label,0,h);
            }
            else
            {
                label.setText("Hour "+String.valueOf(h));
                gridPane.add(label,0,h);
            }
            label.setAlignment(Pos.CENTER);
        }
        for (int d=0;d<m_MaxAmounts.getAmountOfDays()+1;d++)
        {
            gridPane.addColumn(d);
            if(d>0)
            {
                Label label=new Label();
                label.setText("Day "+String.valueOf(d));
                gridPane.add(label,d,0);
                label.setAlignment(Pos.CENTER);
            }
        }
        gridPane.getColumnConstraints().forEach(col->col.setHalignment(HPos.CENTER));
        gridPane.getRowConstraints().forEach(row->row.setValignment(VPos.CENTER));
        return gridPane;
    }
}
