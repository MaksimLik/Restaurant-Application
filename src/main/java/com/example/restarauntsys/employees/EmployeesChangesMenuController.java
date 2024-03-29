package com.example.restarauntsys.employees;

import java.sql.*;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Menu;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeesChangesMenuController extends DB_Handler {
    @FXML
    private TextField price_field;
    @FXML
    private TextField name_field;
    @FXML
    private TextField kcal_field;
    @FXML
    private TextArea about_product;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Menu> table_menu;
    @FXML
    private TableColumn<Menu, Integer> id_table;
    @FXML
    private TableColumn<Menu, Double> kcal_table;
    @FXML
    private TableColumn<Menu, Double> price_table;
    @FXML
    private TableColumn<Menu, String> name_table;
    @FXML
    private TableColumn<Menu, String> table_description;
    ObservableList<Menu> listM;
    private Menu menu;

    @FXML
    public void initialize() {
        initData();
        addButton.setOnAction(event -> {
            addNewProduct();
            initData();
        });

        deleteButton.setOnAction(event -> {
            deleteProduct();
        });

    }
    private void deleteProduct() {
     /*   ObservableList<Menu> allMenu, singleMenu;
        allMenu = table_menu.getItems();
        singleMenu = table_menu.getSelectionModel().getSelectedItems();
        singleMenu.forEach(allMenu::remove); */

        try {
            menu = table_menu.getSelectionModel().getSelectedItem();
            String select = "delete from menu where id_food = " + menu.getId();

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            alertWarningDelete();
        }  catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            alertProcedureOfAction();
        }
        initData();
    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        id_table.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("id"));
        name_table.setCellValueFactory(new PropertyValueFactory<Menu, String>("name"));
        table_description.setCellValueFactory(new PropertyValueFactory<Menu, String>("description"));
        kcal_table.setCellValueFactory(new PropertyValueFactory<Menu, Double>("kcal"));
        price_table.setCellValueFactory(new PropertyValueFactory<Menu, Double>("price"));


        listM = db_handler.getMenu();
        table_menu.setItems(listM);
    }

    private void addNewProduct() {
        DB_Handler db_handler = new DB_Handler();

        try {
            String name = name_field.getText();
            String description = about_product.getText();
            double kcal = Double.parseDouble(kcal_field.getText());
            double price = Double.parseDouble(price_field.getText());
            if (!name.equals("")) {
                Menu menu = new Menu(name, description, kcal, price);
                db_handler.registrationProduct(menu);
                name_field.clear();
                price_field.clear();
                kcal_field.clear();
                about_product.clear();
            } else {
                alertWarningIsEmpty();
            }
        } catch (NumberFormatException e) {
            alertWarningIsEmpty();
        }
    }

}
