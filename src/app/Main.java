package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/view/mainDesign.fxml")));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 370, 600));
        primaryStage.show();

        // blocking resize
        primaryStage.setScene(primaryStage.getScene());
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        // setting app icon
        primaryStage.getIcons().add(new Image("/resources/images/appBoldIcon.png"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}