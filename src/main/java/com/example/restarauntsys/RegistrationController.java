package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logout_ButtonFirst;

    @FXML
    private Button registrationButtonTwo;
    private Scene firstScene;
    public void setFirstScene(Scene scene) {
        firstScene = scene;
    }

    @FXML
    void openFirstScene(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(firstScene);
    }

    @FXML
    void initialize() {
        assert logout_ButtonFirst != null : "fx:id=\"logout_ButtonFirst\" was not injected: check your FXML file 'RegistrationMenu.fxml'.";
        assert registrationButtonTwo != null : "fx:id=\"registrationButtonTwo\" was not injected: check your FXML file 'RegistrationMenu.fxml'.";

    }

}
