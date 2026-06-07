package edupjamas.s30338.gui.view;

import edupjamas.s30338.entity.Wielodziedziczenie.Candidate;
import edupjamas.s30338.entity.zAtrybutem.Adress;
import edupjamas.s30338.gui.START;
import edupjamas.s30338.gui.ViewManager;
import edupjamas.s30338.service.CandidateService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddCandidateView {
    private ViewManager viewManager;

    // pola formularza do uzupelnienia
    private TextField firstNameField;
    private TextField secondNameField;   // opcjonalne drugie imię
    private TextField surnameField;
    private TextField emailField;
    private TextField phoneField;
    private DatePicker birthDatePicker;

    // Adres
    private TextField streetField;
    private TextField buildingNumberField;
    private TextField apartmentNumberField;
    private TextField postalCodeField;
    private TextField cityField;
    private TextField countryField;

    // Dane dodatkowe (Candidate)
    private TextField experienceYearsField;
    private TextField educationField;

    private Button saveButton;
    private Button cancelButton;
    public AddCandidateView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public BorderPane getView(){
        BorderPane root = new BorderPane();

        // ============ GÓRNY PASEK ==============
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #144d2a;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Dodawanie nowego kandydata");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button homeButton = new Button("HOME");
        homeButton.setOnAction(e -> viewManager.setView(new HomeView(viewManager, null).getView()));

        topBar.getChildren().addAll(title, spacer, homeButton);

        // ========== formularz =============
        GridPane formGrid = new GridPane();
        formGrid.setPadding(new Insets(20));
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setStyle("-fx-background-color: #63a57e;");

        // ===== Inicjalizacja pól ==========
        firstNameField = new TextField();
        secondNameField = new TextField();
        surnameField = new TextField();
        emailField = new TextField();
        phoneField = new TextField();
        birthDatePicker = new DatePicker();

        streetField = new TextField();
        buildingNumberField = new TextField();
        apartmentNumberField = new TextField();
        postalCodeField = new TextField();
        cityField = new TextField();
        countryField = new TextField();

        experienceYearsField = new TextField();
        educationField = new TextField();

        // ========== Etykiety i pola ===========
        int row = 0;
        formGrid.add(new Label("Imie:"), 0, row);
        formGrid.add(firstNameField, 1, row);
        formGrid.add(new Label("Drugie imie (opcjonalne):"), 2, row);
        formGrid.add(secondNameField, 3, row);

        row++;
        formGrid.add(new Label("Nazwisko:"), 0, row);
        formGrid.add(surnameField, 1, row);
        formGrid.add(new Label("Email:"), 2, row);
        formGrid.add(emailField, 3, row);

        row++;
        formGrid.add(new Label("Telefon (+48 XXX-XXX-XXX) (opcjonalne):"), 0, row);
        formGrid.add(phoneField, 1, row);
        formGrid.add(new Label("Data urodzenia:"), 2, row);
        formGrid.add(birthDatePicker, 3, row);

        row++;
        Label addressLabel = new Label("Adres zamieszkania:");
        addressLabel.setStyle("-fx-font-weight: bold;");
        formGrid.add(addressLabel, 0, row, 4, 1);

        row++;
        formGrid.add(new Label("Ulica:"), 0, row);
        formGrid.add(streetField, 1, row);
        formGrid.add(new Label("Numer budynku:"), 2, row);
        formGrid.add(buildingNumberField, 3, row);

        row++;
        formGrid.add(new Label("Numer mieszkania (opcjonalne jako 0):"), 0, row);
        formGrid.add(apartmentNumberField, 1, row);
        formGrid.add(new Label("Kod pocztowy:"), 2, row);
        formGrid.add(postalCodeField, 3, row);

        row++;
        formGrid.add(new Label("Miasto:"), 0, row);
        formGrid.add(cityField, 1, row);
        formGrid.add(new Label("Kraj:"), 2, row);
        formGrid.add(countryField, 3, row);

        row++;
        Label extraLabel = new Label("Dodatkowe dane kandydata:");
        extraLabel.setStyle("-fx-font-weight: bold;");
        formGrid.add(extraLabel, 0, row, 4, 1);

        row++;
        formGrid.add(new Label("Staż (lata):"), 0, row);
        formGrid.add(experienceYearsField, 1, row);
        formGrid.add(new Label("Wykształcenie (kierunek):"), 2, row);
        formGrid.add(educationField, 3, row);

        // ========== Przyciski ==========
        HBox buttonBar = new HBox(20);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.setPadding(new Insets(20));

        saveButton = new Button("Zapisz kandydata");
        saveButton.setStyle("-fx-background-color: #027d34; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        saveButton.setOnAction(e -> saveCandidate());

        cancelButton = new Button("Anuluj");
        cancelButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        cancelButton.setOnAction(e ->
                        viewManager.setView(
                                new HomeView(viewManager,getCandidateService()).getView()
                        ));

        buttonBar.getChildren().addAll(saveButton, cancelButton);

        // =========  kontener ===========
        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(formGrid, buttonBar);
        centerBox.setStyle("-fx-background-color: #63a57e;");

        root.setTop(topBar);
        root.setCenter(centerBox);


        return root;
    }

    private CandidateService getCandidateService() {
        return START.context.getBean(CandidateService.class);
    }

    private void saveCandidate() {
        // ======== walidacja =======
        if(firstNameField.getText().isEmpty() || surnameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || birthDatePicker.getValue() == null ||
                streetField.getText().isEmpty() || buildingNumberField.getText().isEmpty() ||
                postalCodeField.getText().isEmpty() || cityField.getText().isEmpty() ||
                countryField.getText().isEmpty()) {

            showAlert("Błąd", "Wszystkie pola oznaczone jako wymagane muszą być wypełnione.");
            return;
        }

        try {
            // Tworzenie listy imion (pierwsze + opcjonalne drugie)
            List<String> names = new ArrayList<>();
            names.add(firstNameField.getText().trim());
            if (!secondNameField.getText().trim().isEmpty()) {
                names.add(secondNameField.getText().trim());
            }

            // Adres
            Adress address = new Adress(
                    streetField.getText().trim(),
                    Integer.parseInt(buildingNumberField.getText().trim()),
                    apartmentNumberField.getText().trim().isEmpty() ? null : Integer.parseInt(apartmentNumberField.getText().trim()),
                    postalCodeField.getText().trim(),
                    cityField.getText().trim(),
                    countryField.getText().trim()
            );

            // Dane kandydata
            Integer experience = null;
            if (!experienceYearsField.getText().trim().isEmpty()) {
                experience = Integer.parseInt(experienceYearsField.getText().trim());
            }
            String education = educationField.getText().trim();
            if (education.isEmpty()) education = null;

            Candidate candidate = new Candidate(
                    names,
                    surnameField.getText().trim(),
                    emailField.getText().trim(),
                    phoneField.getText().trim().isEmpty() ? null : phoneField.getText().trim(),
                    birthDatePicker.getValue(),
                    List.of(address),   // lista adresów
                    null,              // applications (puste na początku)
                    experience,
                    education
            );

            // Powiązanie zwrotne adresu z kandydatem
            address.setPerson(candidate);
            // Zapis przez serwis
            CandidateService candidateService = getCandidateService();
            candidateService.saveCandidate(candidate);

            showAlert("Sukces", "Kandydat został pomyślnie dodany.");
            // Powrót do listy kandydatów
            viewManager.setView(new CandidatesView(viewManager, candidateService).getView());

        } catch (NumberFormatException e) {
            showAlert("Błąd", "Nieprawidłowy format liczby (np. numer budynku, staż).");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Błąd", "Nie udało się zapisać kandydata: " + e.getMessage());
        }
    }

    private void showAlert(String blas, String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(blas);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
}
