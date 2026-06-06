package edupjamas.s30338.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class START extends Application {
    @Override
    public void start(Stage stage) {

        Label label = new Label("Hello JavaFX 👋");

        VBox root = new VBox(label);

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("MAS - JavaFX Start");
        stage.setScene(scene);

        stage.show();
    }
}
