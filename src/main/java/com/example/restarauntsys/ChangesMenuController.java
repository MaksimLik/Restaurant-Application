package com.example.restarauntsys;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Menu;
import com.example.restarauntsys.tables.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ChangesMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField price_field;
    @FXML
    private TextField name_field;
    @FXML
    private TextField kcal_field;
    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;
    @FXML
    private TableView<Menu> table_menu;
    @FXML
    private TableColumn<Menu, Integer> id_table;

    @FXML
    private TableColumn<Menu, Float> kcal_table;

    @FXML
    private TableColumn<Menu, String> name_table;

    @FXML
    private TableColumn<Menu, Float> price_table;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            addNewProduct();
        });

    }
    private void addNewProduct() {
        DB_Handler db_handler = new DB_Handler();

        try {
            String name = name_field.getText();
            float kcal = Float.parseFloat(kcal_field.getText());
            float price = Float.parseFloat(price_field.getText());
            if (!name.equals("")) {
                Menu menu = new Menu(name, kcal, price);
                db_handler.registrationProduct(menu);
            } else {
                errorAlarm();
            }
        } catch (NumberFormatException e) {
            errorAlarm();
        }

    }

    private void errorAlarm () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Field with name, price or kcal is empty!");
        alert.setContentText("Please, write all information about product");
        alert.showAndWait();
    }


}
