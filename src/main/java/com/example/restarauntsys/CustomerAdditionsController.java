package com.example.restarauntsys;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerAdditionsController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button logoutButton;
    @FXML
    private TableView<?> table_menu;
    @FXML
    private TableColumn<?, ?> id_table;
    @FXML
    private TableColumn<?, ?> name_table;
    @FXML
    private TableColumn<?, ?> price_table;

    private Scene thirdScene;
    public void setThirdScene(Scene scene) {
        thirdScene = scene;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(thirdScene);
        });
    }
}