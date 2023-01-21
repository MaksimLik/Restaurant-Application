package com.example.restarauntsys;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeesStartController {
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
            InitWindow("changesMenu.fxml");
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

    private void InitWindow (String window) {
        FXMLLoader fxmlLoader  = new FXMLLoader(getClass().getResource(window));
        Parent windowPane = null;

        try {
            windowPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Restaurant Application");
        stage.setScene(new Scene(windowPane));
        stage.showAndWait();
    }

}
