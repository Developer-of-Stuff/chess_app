import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL primaryScenePath = getClass().getResource("gui/mainPage.fxml");
        assert primaryScenePath != null;
        Scene scene = new Scene(FXMLLoader.load(primaryScenePath));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess Application");
        primaryStage.show();
    }
}
