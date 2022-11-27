package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;
    private Scene secondScene;
    @FXML
    private Button registrationFirstButton;

    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }
    @FXML
    void openSecondScene(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
    }

    @FXML
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'StartMenu.fxml'.";
        assert registrationFirstButton != null : "fx:id=\"registrationFirstButton\" was not injected: check your FXML file 'StartMenu.fxml'.";

    }

}
