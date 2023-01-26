package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Additions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

public class EmployeesAdditionsController extends DB_Handler implements Initializable{
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableColumn<Additions, Integer> id_table;
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

    ObservableList<Additions> listA;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        addButton.setOnAction(event -> {
            addNewProduct();
            initData();
        });

        deleteButton.setOnAction(event -> {
            deleteProduct();
            initData();
        });
    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        id_table.setCellValueFactory(new PropertyValueFactory<Additions, Integer>("id"));
        name_table.setCellValueFactory(new PropertyValueFactory<Additions, String>("name"));
        price_table.setCellValueFactory(new PropertyValueFactory<Additions, Double>("price"));

        listA = db_handler.getAdditions();
        table_menu.setItems(listA);
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

    private void deleteProduct() {
        try {
            additions = table_menu.getSelectionModel().getSelectedItem();
            String select = "delete from additions where ID_addition = " + additions.getId();
            System.out.println(select);
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            errorAlarm2();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            warning1();
        }
        initData();
    }

    private void errorAlarm () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Field with name or price is empty!");
        alert.setContentText("Please, write all information about addition");
        alert.showAndWait();
    }

    private void errorAlarm2 () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("You cannot delete this product if it is ordered or commented on by the customer.");
        alert.setContentText("Please, check if this product is ordered, awaiting delivery, or commented.");
        alert.showAndWait();
    }

    private void warningAlarm () {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Please, if you can use float number you must use comma (.) and write all fields");
        alert.setContentText("Please, use COMMA in number and write all fields");
        alert.showAndWait();
    }
    private void warning1() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Please, choose product from table and click him and click button.");
        alert.setContentText("Choose product, click him and click button");
        alert.showAndWait();
    }
}
