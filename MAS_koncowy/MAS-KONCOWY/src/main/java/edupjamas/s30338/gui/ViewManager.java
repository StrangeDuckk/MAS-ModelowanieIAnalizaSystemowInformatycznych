package edupjamas.s30338.gui;

import javafx.scene.layout.BorderPane;

public class ViewManager {
    private final BorderPane root;

    public ViewManager(BorderPane root) {
        this.root = root;
    }
    public void setView(javafx.scene.Node view){
        root.setCenter(view);
    }
}
