package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployeesStartController implements Initializable {

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changesPanelButton.setOnAction(event -> {
            openNewWindow();
        });

    }

    private void openNewWindow () {
        changesPanelButton.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(fiveScene);
            }
        );
    }

}
