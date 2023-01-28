package com.example.restarauntsys.employees;

import com.example.restarauntsys.mysql.Alerts;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployeesStartController extends Alerts {
    @FXML
    private Button commentsButton;
    @FXML
    private Button showOrdersButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button changesPanelButton;
    @FXML
    private Button additionsButton;
    private Scene FirstScene;

    public void setFirstScene(Scene scene) {
        FirstScene = scene;
    }
    @FXML
    public void initialize() {
        changesPanelButton.setOnAction(event -> {
            InitWindow("EmployeesChangesMenu.fxml");
        });
        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(FirstScene);
        });

        showOrdersButton.setOnAction(event -> {
            InitWindow("EmployeesOrdersMenu.fxml");
        });

        additionsButton.setOnAction(event -> {
            InitWindow("EmployeesAdditionsMenu.fxml");
        });

        commentsButton.setOnAction(event -> {
            InitWindow("EmployeesShowComments.fxml");
        });

    }

}
