package edupjamas.s30338.gui.view;

import edupjamas.s30338.gui.ViewManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class CandidatesView {
    private final ViewManager viewManager;
    private String buttorNormalStyle =
            "-fx-background-color: #027d34;"+
                    "-fx-text-fill: white;"+
                    "-fx-font-size: 20px;"+
                    "-fx-font-weight: bold";
    private String buttorClickedStyle =
            "-fx-background-color: #027d34;"+
                    "-fx-text-fill: white;"+
                    "-fx-font-size: 20px;"+
                    "-fx-font-weight: bold";

    public CandidatesView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public BorderPane getView(){
        BorderPane root = new BorderPane();

        // ============ gorny pasek ==============
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #144d2a;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Wylistowanie wszystkich kandydatów");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button home = new Button("HOME");

        home.setOnAction(e ->
                viewManager.setView(
                        new HomeView(viewManager).getView()
                )
        );

        topBar.getChildren().addAll(title, spacer, home);


        // ============ rozdzielenie srodka ==============
        HBox main = new HBox();
        main.setSpacing(10);
        main.setPadding(new Insets(10));

        VBox left = new VBox(10);
        VBox right = new VBox(10);

        left.setPrefWidth(300);
        right.setPrefWidth(500);

        left.setStyle("-fx-background-color: #63a57e;");
        right.setStyle("-fx-background-color: #63a57e;");

        // ============ DANE ==============
        for (int i = 1; i <= 7; i++) {

            String name = "Kandydat " + i;

            Button b = new Button(name);
            b.setMaxWidth(Double.MAX_VALUE);
            b.setStyle(buttorNormalStyle);

            b.setOnAction(e -> {

                // zmiana koloru na klikniecie
                left.getChildren().forEach(node -> node.setStyle(buttorNormalStyle));
                b.setStyle(buttorClickedStyle);

                loadOffers(right, name);
            });

            left.getChildren().add(b);
        }

        // ========== zlozenie wszystkiego =============
        main.getChildren().addAll(left, right);

        root.setTop(topBar);
        root.setCenter(main);


        return root;
    }

    private void loadOffers(VBox right, String candidateName) {
        right.getChildren().clear();

        Label header = new Label("Oferty dla: " + candidateName);
        header.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        right.getChildren().add(header);

        // mock danych (tu później repozytorium + Hibernate)
        for (int i = 1; i <= 4; i++) {

            Button offer = new Button("Aplikacja " + i);
            offer.setMaxWidth(Double.MAX_VALUE);
            offer.setStyle("-fx-background-color: #bfffd1;");

            right.getChildren().add(offer);
        }
    }
}
