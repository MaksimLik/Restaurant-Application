package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CustomerOrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private RadioButton deliveryButton;

    @FXML
    private TableColumn<?, ?> id_table;

    @FXML
    private TableColumn<?, ?> kcal_table;

    @FXML
    private TableColumn<?, ?> name_table;

    @FXML
    private TableColumn<?, ?> price_table;

    @FXML
    private TableColumn<?, ?> table_description;

    @FXML
    private TableView<?> table_menu;
    private Scene thirdScene;
    public void setThirdScene(Scene scene) {
        thirdScene = scene;
    }

    @FXML
    void backButton(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(thirdScene);
    }

    @FXML
    void initialize() {


    }

}