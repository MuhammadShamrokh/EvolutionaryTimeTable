package BestSolutionPrinter;

import javafx.animation.StrokeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class LessonController {

    @FXML
    private Rectangle shape;

    @FXML
    private Label hourLabel;

    @FXML
    private Label classLabel;

    @FXML
    private Label teacherLabel;

    @FXML
    private Label dayLabel;

    @FXML
    private Label subjectLabel;

    public void setHourText(String i_Text) {
        this.hourLabel.setText(i_Text);
    }

    public void setClassText(String i_Text) {
        this.classLabel.setText(i_Text);
    }

    public void setTeacherText(String i_Text) {
        this.teacherLabel.setText(i_Text);
    }

    public void setDayText(String i_Text) {
        this.dayLabel.setText(i_Text);
    }

    public void setSubjectText(String i_Text) {
        this.subjectLabel.setText(i_Text);
    }

    public void startStrokeTransition()
    {
        StrokeTransition strokeTransition=new StrokeTransition(Duration.seconds(2),shape, Color.GREEN,Color.RED);
        strokeTransition.setAutoReverse(true);
        strokeTransition.setCycleCount(4);
        strokeTransition.play();
    }
}
