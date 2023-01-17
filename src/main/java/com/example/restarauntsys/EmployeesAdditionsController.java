package com.example.restarauntsys;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesAdditionsController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> id_table;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField name_field;

    @FXML
    private TableView<?> table_menu;

    @FXML
    private TableColumn<?, ?> name_table;
    @FXML
    private TableColumn<?, ?> price_table;
    @FXML
    private TextField price_field;

    private Scene fourScene;
    public void setFourScene(Scene scene) {
        fourScene = scene;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(fourScene);
        });
    }
}
