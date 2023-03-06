package Application;

import FilePrinter.FilePrinterController;
import Manager.LogicEngineManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    private LogicEngineManager m_Engine;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource("ETTUserInterface.fxml");
        fxmlLoader.setLocation(url);
        Parent root = fxmlLoader.load(url.openStream());
        ApplicationController controller=(ApplicationController)fxmlLoader.getController();
        controller.setStage(primaryStage);
        m_Engine=new LogicEngineManager();
        controller.setEngine(m_Engine);

        fxmlLoader = new FXMLLoader();
        url = getClass().getResource("/FilePrinter/FilePrinterComponent.fxml");
        fxmlLoader.setLocation(url);
        GridPane fileComponentRoot=fxmlLoader.load(url.openStream());
        FilePrinterController filePrinterController=(FilePrinterController) fxmlLoader.getController();

        controller.setFilePrinterController(filePrinterController);

        Scene scene = new Scene(root, Pane.USE_COMPUTED_SIZE, Pane.USE_COMPUTED_SIZE);
        primaryStage.setTitle("Evolutionary Time Table");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);

        primaryStage.show();
    }
}
