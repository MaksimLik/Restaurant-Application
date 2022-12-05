package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployeesStartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;
    @FXML
    private Button changesPanelButton;

    private Scene fiveScene;
    public void setFiveScene(Scene scene) {
        fiveScene = scene;
    }


    @FXML
    void initialize() {
        changesPanelButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(fiveScene);
        });

    }

}
