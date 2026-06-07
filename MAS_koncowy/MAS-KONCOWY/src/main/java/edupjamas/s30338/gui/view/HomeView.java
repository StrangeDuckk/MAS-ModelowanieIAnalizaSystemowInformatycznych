package edupjamas.s30338.gui.view;

import edupjamas.s30338.gui.START;
import edupjamas.s30338.gui.ViewManager;
import edupjamas.s30338.service.CandidateService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HomeView {
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
    private CandidateService candidateService;//todo co z tym

    public HomeView(ViewManager viewManager, CandidateService candidateService) {
        this.viewManager = viewManager;
        this.candidateService = candidateService;
    }

    public GridPane getView(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30));
        gridPane.setHgap(35);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        Button button1 = createMenuButton("Dodanie nowej aplikacji");
        button1.setOnAction(event -> {
            button1.setStyle(buttorClickedStyle);
            viewManager.setView(
                    new CandidatesView(viewManager, candidateService).getView()
            );//bo potrzeba wybrania kandydata z listy
        });
        Button button2 = createMenuButton("Wprowadzenie nowego adresu");
        Button button3 = createMenuButton("Dodane nowego ogłoszenia o pracę");
        Button button4 = createMenuButton("Dodanie nowego kandydata");
        button4.setOnAction(e ->{
            button4.setStyle(buttorClickedStyle);
            viewManager.setView(
                    new AddCandidateView(viewManager).getView()
            );
        });
        Button button5 = createMenuButton("Wylistowanie wszystkich kandydatow");
        button5.setOnAction(e -> {
            button5.setStyle(buttorClickedStyle);
            candidateService = START.context.getBean(CandidateService.class);
            viewManager.setView(
                    new CandidatesView(viewManager, candidateService).getView()
            );
        });
        Button button6 = createMenuButton("Dodanie nowej firmy");
        Button button7 = createMenuButton("Dodanie nowego pracownika agencji");
        Button button8 = createMenuButton("Wylistowanie wszystkich ofert pracy");
        Button button9 = createMenuButton("Wylistowanie wszystkich firm i zmiana adresu firmy");

        gridPane.add(button1, 0,0);
        gridPane.add(button2, 1,0);
        gridPane.add(button3, 2,0);

        gridPane.add(button4, 0,1);
        gridPane.add(button5, 1,1);
        gridPane.add(button6, 2,1);

        gridPane.add(button7, 0,2);
        gridPane.add(button8, 1,2);
        gridPane.add(button9, 2,2);

        return gridPane;
    }
    private Button createMenuButton(String tekstPrzycisku) {
        Button button = new Button(tekstPrzycisku);

        button.setPrefSize(220,120);
        button.setWrapText(true);

        button.setStyle(buttorNormalStyle);

        return button;
    }
}
