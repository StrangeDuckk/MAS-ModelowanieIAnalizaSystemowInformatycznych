package edupjamas.s30338.gui.view;

import edupjamas.s30338.entity.Wielodziedziczenie.Candidate;
import edupjamas.s30338.entity.kwalifikowana.Application;
import edupjamas.s30338.entity.kompozycja.JobOffer;  // dostosuj pakiet
import edupjamas.s30338.gui.START;
import edupjamas.s30338.gui.ViewManager;
import edupjamas.s30338.service.ApplicationService;
import edupjamas.s30338.service.CandidateService;
import edupjamas.s30338.service.JobOfferService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddApplicationView {
    private Candidate candidate;
    private ViewManager viewManager;

    // ==========  Pola formularza ==========
    private DatePicker datePicker;
    private TextField salaryField;
    private ComboBox<JobOffer> jobOfferCombo;
    private TextArea skillsArea;
    private TextArea certificationsArea;
    private TextArea CVcourses;

    private Button saveButton;
    private Button cancelButton;
    public AddApplicationView(ViewManager viewManager, Candidate obecnieWybranyKandydat) {
        this.candidate = obecnieWybranyKandydat;
        this.viewManager = viewManager;
    }

    public BorderPane getView() {
        BorderPane root = new BorderPane();

        // ============ Gorny ==============
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #144d2a;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Dodawanie nowej aplikacji dla: " + candidate.getSurname() + " (" + candidate.getEmail() + ")");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button homeButton = new Button("HOME");
        homeButton.setOnAction(e -> viewManager.setView(new HomeView(viewManager, null).getView()));

        topBar.getChildren().addAll(title, spacer, homeButton);

        // ============ formularz ==============
        GridPane formGrid = new GridPane();
        formGrid.setPadding(new Insets(20));
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setStyle("-fx-background-color: #63a57e;");

        // ======= pola ==============
        datePicker = new DatePicker(LocalDate.now());
        salaryField = new TextField();
        jobOfferCombo = new ComboBox<>();
        skillsArea = new TextArea();
        skillsArea.setPrefRowCount(3);
        certificationsArea = new TextArea();
        certificationsArea.setPrefRowCount(3);
        CVcourses = new TextArea();

        // ========== sciagniecie ofert pracy ============
        loadJobOffers();

        // ========== formularz ulozenie =========
        int row = 0;
        formGrid.add(new Label("Data aplikacji:"), 0, row);
        formGrid.add(datePicker, 1, row);
        formGrid.add(new Label("Proponowane wynagrodzenie (PLN):"), 2, row);
        formGrid.add(salaryField, 3, row);

        row++;
        formGrid.add(new Label("Oferta pracy:"), 0, row);
        formGrid.add(jobOfferCombo, 1, row, 3, 1);

        row++;
        formGrid.add(new Label("edukacja (oddziel przecinkami, opcjonalne):"), 0, row);
        formGrid.add(skillsArea, 1, row, 3, 1);

        row++;
        formGrid.add(new Label("doswiadczenie (oddziel przecinkami, opcjonalne):"), 0, row);
        formGrid.add(certificationsArea, 1, row, 3, 1);

        row++;
        formGrid.add(new Label("kursy cv (oddziel przecinkami, opcjonalnie):"), 0, row);
        formGrid.add(CVcourses, 1, row);

        // Przyciski
        HBox buttonBar = new HBox(20);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.setPadding(new Insets(20));

        saveButton = new Button("Zapisz aplikację");
        saveButton.setStyle("-fx-background-color: #027d34; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        saveButton.setOnAction(e -> saveApplication());

        cancelButton = new Button("Anuluj");
        cancelButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        cancelButton.setOnAction(e -> backToCandidatesView());

        buttonBar.getChildren().addAll(saveButton, cancelButton);

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(formGrid, buttonBar);
        centerBox.setStyle("-fx-background-color: #63a57e;");

        root.setTop(topBar);
        root.setCenter(centerBox);

        return root;
    }

    private void backToCandidatesView() {
        CandidateService candidateService = START.context.getBean(CandidateService.class);
        viewManager.setView(new CandidatesView(viewManager, candidateService).getView());
    }

    private void saveApplication() {
        // Walidacja
        if (datePicker.getValue() == null) {
            showAlert("Błąd", "Data aplikacji jest wymagana.");
            return;
        }
        if (salaryField.getText().isEmpty()) {
            showAlert("Błąd", "Proponowane wynagrodzenie jest wymagane.");
            return;
        }
        if (jobOfferCombo.getValue() == null) {
            showAlert("Błąd", "Wybierz ofertę pracy.");
            return;
        }

        double salary;
        try {
            salary = Double.parseDouble(salaryField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Błąd", "Wynagrodzenie musi być liczbą.");
            return;
        }

        // Przetwarzanie list
        List<String> skills = parseCommaSeparated(skillsArea.getText());
        List<String> certifications = parseCommaSeparated(certificationsArea.getText());
        List<String> cvCourses = parseCommaSeparated(CVcourses.getText());

        JobOffer selectedOffer = jobOfferCombo.getValue();

        // Tworzenie aplikacji
        Application application = new Application(
                datePicker.getValue(),
                salary,
                candidate,
                selectedOffer,
                skills,
                certifications,
                cvCourses
        );

        // Zapis przez serwis
        try {
            ApplicationService appService = START.context.getBean(ApplicationService.class);
            appService.saveApplication(application);
            showAlert("Sukces", "Aplikacja została dodana.");
            backToCandidatesView();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Błąd", "Nie udało się zapisać aplikacji: " + e.getMessage());
        }
    }

    private List<String> parseCommaSeparated(String text) {
        if (text == null || text.trim().isEmpty()) return List.of();
        return Arrays.stream(text.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }


    private void loadJobOffers() {
        try {
            JobOfferService jobOfferService = START.context.getBean(JobOfferService.class);
            List<JobOffer> offers = jobOfferService.getAllJobOffers();
            jobOfferCombo.setItems(FXCollections.observableArrayList(offers));
            // ===== nazwy ofert w combobox===========
            jobOfferCombo.setCellFactory(lv -> new ListCell<JobOffer>() {
                @Override
                protected void updateItem(JobOffer item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getName());
                }
            });
            jobOfferCombo.setButtonCell(new ListCell<JobOffer>() {
                @Override
                protected void updateItem(JobOffer item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("blad", "Nie udalo się pobrac listy ofert pracy.");
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
