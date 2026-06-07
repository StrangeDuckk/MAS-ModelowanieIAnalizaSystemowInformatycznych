package edupjamas.s30338.gui;

import edupjamas.s30338.MasKoncowyApplication;
import edupjamas.s30338.gui.view.HomeView;
import edupjamas.s30338.service.CandidateService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class START extends Application {
    public static ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) {

        // ============================ tlo aplikacji ============================
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #aaf1be;");

        ViewManager viewManager = new ViewManager(root);

        // ============================ gorny pasek ============================
        HBox gornyPasek = new HBox();
        gornyPasek.setPadding(new Insets(10));
        gornyPasek.setSpacing(20);
        gornyPasek.setAlignment(Pos.CENTER_LEFT);
        gornyPasek.setStyle("-fx-background-color: #144d2a;");

        Region spacerLeft = new Region();
        Region spacerRight = new Region();
        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
        HBox.setHgrow(spacerRight, Priority.ALWAYS);

        Label title = new Label("PreczBezrobocie");
        title.setStyle(
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 26px;" +
                        "-fx-font-weight: bold;"
        );

        Button homeButton = new Button("HOME");
        homeButton.setPrefSize(120,40);
        homeButton.setDisable(true);

        gornyPasek.getChildren().addAll(
                homeButton,
                spacerLeft,
                title,
                spacerRight
        );

        // ============================ srodek - przyciski ============================
        CandidateService candidateService = context.getBean(CandidateService.class);

        HomeView homeView = new HomeView(viewManager, candidateService);

        // ============================ ustawienie wszystkiego ============================
        root.setTop(gornyPasek);
        root.setCenter(homeView.getView());

        Scene scene = new Scene(root, 1000,650);

        stage.setTitle("PreczBezrobocie");
        stage.setScene(scene);
        stage.show();
    }
}