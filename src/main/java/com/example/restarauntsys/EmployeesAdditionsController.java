package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Additions;
import com.example.restarauntsys.tables.Menu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesAdditionsController extends DB_Handler implements Initializable{

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
    private Additions additions;
    @FXML
    private TableView<Additions> table_menu;
    @FXML
    private TableColumn<Additions, String> name_table;
    @FXML
    private TableColumn<Additions, Double> price_table;
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

        addButton.setOnAction(event -> {
            addNewProduct();
        });
    }

    private void addNewProduct() {
        DB_Handler db_handler = new DB_Handler();

        try {
            String name = name_field.getText();
            double price = Double.parseDouble(price_field.getText());
            if (!name.equals("")) {
                Additions additions = new Additions(name, price);
                db_handler.registrationAddition(additions);
                name_field.clear();
                price_field.clear();
            } else {
                errorAlarm();
            }
        } catch (NumberFormatException e) {
            warningAlarm();
        }

    }

    private void errorAlarm () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Field with name, price or kcal is empty!");
        alert.setContentText("Please, write all information about product");
        alert.showAndWait();
    }

    private void warningAlarm () {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Please, if you can use float number you must use comma (.)");
        alert.setContentText("Please, use COMMA");
        alert.showAndWait();
    }
}
