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
    private Button logoutButton;
    @FXML
    private Button changesPanelButton;
    @FXML
    private Button additionsButton;
    private Scene FirstScene;
    private Scene nineScene;
    private Scene fiveScene;
    private Scene tenScene;
    public void setFiveScene(Scene scene) {
        fiveScene = scene;
    }
    public void setFirstScene(Scene scene) {
        FirstScene = scene;
    }
    public void setNineScene(Scene scene) {
        nineScene = scene;
    }
    public void setTenScene (Scene scene) {
        tenScene = scene;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changesPanelButton.setOnAction(event -> {
            openNewWindow();
        });
        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(FirstScene);
        });

        addButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(nineScene);
        });

        additionsButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(tenScene);
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
