package RulesPrinter;

import javafx.animation.StrokeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class RuleController {

    @FXML private Rectangle shape;

    @FXML
    private Text ruleNameText;

    @FXML
    private Label ruleTypeLabel;

    @FXML
    private Label ruleParamLabel;

    @FXML
    private Label ruleScoreLabel;

    public void setRuleNameText(String i_Text)
    {
        ruleNameText.setText(i_Text);
    }

    public void setRuleTypeLabel(String i_Text)
    {
        ruleTypeLabel.setText(i_Text);
    }

    public void setRuleParamLabel(String i_Text)
    {
        ruleParamLabel.setText(i_Text);
    }

    public void setRuleScoreLabel(String i_Text)
    {
        ruleScoreLabel.setText(i_Text);
    }

    public void startStrokeTransition()
    {
        StrokeTransition strokeTransition=new StrokeTransition(Duration.seconds(2),shape, Color.GREEN,Color.RED);
        strokeTransition.setAutoReverse(true);
        strokeTransition.setCycleCount(4);
        strokeTransition.play();
    }
}
