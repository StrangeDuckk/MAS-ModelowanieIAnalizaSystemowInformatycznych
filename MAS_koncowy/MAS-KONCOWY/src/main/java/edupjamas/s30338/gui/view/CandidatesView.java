package edupjamas.s30338.gui.view;

import edupjamas.s30338.entity.Wielodziedziczenie.Candidate;
import edupjamas.s30338.entity.kwalifikowana.Application;
import edupjamas.s30338.gui.ViewManager;
import edupjamas.s30338.service.CandidateService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.util.List;

public class CandidatesView {
    private final ViewManager viewManager;
    private final CandidateService candidateService;
    private Candidate obecnieWybranyKandydat = null;
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

    public CandidatesView(ViewManager viewManager, CandidateService candidateService) {
        this.viewManager = viewManager;
        this.candidateService = candidateService;
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
                        new HomeView(viewManager,candidateService).getView()
                )
        );

        topBar.getChildren().addAll(title, spacer, home);

        // ============ przyciski nawigacyjne ================
        HBox actionBar = new HBox(20);
        actionBar.setPadding(new Insets(10));
        actionBar.setAlignment(Pos.CENTER);
        actionBar.setStyle("-fx-background-color: #2c5a3e;");

        Button searchButton = new Button("Wyszukiwanie nowego kandydata");
        searchButton.setStyle(buttorNormalStyle);
        searchButton.setDisable(true); // nieklikalny

        Button addCandidateButton = new Button("Dodanie nowego kandydata");
        addCandidateButton.setStyle(buttorClickedStyle);
        addCandidateButton.setOnAction(e -> {
            addCandidateButton.setStyle(buttorClickedStyle);
            viewManager.setView(new AddCandidateView(viewManager).getView());
        });

        Button addApplicationButton = new Button("Dodanie nowej aplikacji");
        addApplicationButton.setStyle(buttorClickedStyle);
        addApplicationButton.setOnAction(e -> {
            if(obecnieWybranyKandydat == null){
                showAlert("Brak wyboru", "Wybierz kandydata z lewej listy aby kontynułowac");
            }
            viewManager.setView(new AddApplicationView(viewManager, obecnieWybranyKandydat).getView());
        });

        actionBar.getChildren().addAll(searchButton, addCandidateButton, addApplicationButton);

        // Łączymy oba paski w VBox
        VBox topContainer = new VBox();
        topContainer.getChildren().addAll(topBar, actionBar);



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
        List<Candidate> candidateList = candidateService.getAllCandidatesWithApplications();//pobranie danych

        for(Candidate candidate: candidateList){
            Button b = new Button(
                    candidate.getSurname()+": "+candidate.getEmail()
            );
            b.setMaxWidth(Double.MAX_VALUE);
            b.setStyle(buttorNormalStyle);

            b.setOnAction(e ->{
                left.getChildren()
                        .forEach(node -> node.setStyle(buttorNormalStyle));
                b.setStyle(buttorClickedStyle);
                obecnieWybranyKandydat = candidate;
                loadOffers(right,candidate);
            });

            left.getChildren().add(b);
        }

        ScrollPane leftScroll = new ScrollPane(left);
        leftScroll.setFitToHeight(true);
        leftScroll.setStyle("-fx-background: #63a57e; -fx-background-color: #63a57e;");

        // ========== zlozenie wszystkiego =============
        main.getChildren().addAll(left, right);
        HBox.setHgrow(right, Priority.ALWAYS);

        root.setTop(topContainer);
        root.setCenter(main);


        return root;
    }

    private void loadOffers(VBox right, Candidate candidate) {
        right.getChildren().clear();

        Label header = new Label("Aplikacja kandydata: "+ candidate.getSurname());
        header.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        right.getChildren().add(header);

        List<Application> applications = candidate.getApplications();

        if (applications == null || applications.isEmpty()) {
            Label empty = new Label("Brak złożonych aplikacji");
            empty.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px;");
            right.getChildren().add(empty);
            return;
        }

        for (Application app : applications) {
            String offerName = (app.getJobOffer() != null) ? app.getJobOffer().getName() : "Brak oferty";

            String details = String.format("%s | Data: %s | Wynagrodzenie: %.2f",
                    offerName, app.getData(), app.getCandidatesSalaryProposition());

            Button appButton = new Button(details);
            appButton.setMaxWidth(Double.MAX_VALUE);
            appButton.setStyle("-fx-background-color: #bfffd1; -fx-text-fill: #000000;");
            appButton.setWrapText(true);
            appButton.setDisable(true);
            right.getChildren().add(appButton);
        }
    }
    private void showAlert(String blas, String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(blas);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
        return;
    }
}
