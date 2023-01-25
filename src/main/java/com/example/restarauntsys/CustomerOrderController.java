package com.example.restarauntsys;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Menu;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.example.restarauntsys.StartController.CustID;

public class CustomerOrderController extends DB_Handler {
    @FXML
    private Button addButton;
    @FXML
    private TableView<Menu> table_menu;
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
    String loginText;

    @FXML
    public void initialize() {
        System.out.println(loginText);
        initData();
        addButton.setOnAction(event -> {
            addFunction();
        });
    }
    private void addFunction() {
        try {
            menu = table_menu.getSelectionModel().getSelectedItem();
            String select = "insert into orders (date_of_order, order_status, Customers_Users_ID_user, Menu_ID_food) " +
                    "values (current_date(), 'in progress', " + CustID + ", " + menu.getId() + ");";
            System.out.println(select);

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            warning2();
        }
    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        name_table.setCellValueFactory(new PropertyValueFactory<Menu, String>("name"));
        table_description.setCellValueFactory(new PropertyValueFactory<Menu, String>("description"));
        kcal_table.setCellValueFactory(new PropertyValueFactory<Menu, Double>("kcal"));
        price_table.setCellValueFactory(new PropertyValueFactory<Menu, Double>("price"));

        listM = db_handler.getMenu();
        table_menu.setItems(listM);
    }

    private void warning2() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Please, choose product from table and click him and click button.");
        alert.setContentText("Choose product, click him and click button");
        alert.showAndWait();
    }

}