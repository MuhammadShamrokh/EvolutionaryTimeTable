package RulesPrinter;

import Application.ApplicationController;
import DataTransferClasses.RuleData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class RulesPrinter {
    private List<RuleData> m_RulesDataList;
    private ScrollPane m_Root;

    public RulesPrinter(List<RuleData> i_RuleDataList, ScrollPane i_Root)
    {
        m_RulesDataList=i_RuleDataList;
        m_Root=i_Root;
    }

    public void showRules(Boolean i_isAnimated)
    {
        VBox vbox=new VBox();
        vbox.setSpacing(10);
        for(RuleData data:m_RulesDataList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL url = getClass().getResource("RuleComponent.fxml");
            fxmlLoader.setLocation(url);
            StackPane root = null;
            try {
                root = fxmlLoader.load(url.openStream());
                RuleController controller = (RuleController) fxmlLoader.getController();
                controller.setRuleNameText(data.getName());
                controller.setRuleTypeLabel(data.getType());
                controller.setRuleScoreLabel(data.getGrade().toString());
                if (data.getName().toUpperCase().equals("SEQUENTIALITY"))
                {
                    controller.setRuleParamLabel(data.getTotalHours().toString());
                }
                else
                {
                    controller.setRuleParamLabel("none");
                }
                if(i_isAnimated)
                    controller.startStrokeTransition();
                vbox.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        m_Root.setContent(vbox);
    }
}
