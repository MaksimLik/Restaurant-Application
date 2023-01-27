package com.example.restarauntsys;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.example.restarauntsys.mysql.DB_Handler;

import com.example.restarauntsys.tables.Orders;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeesOrdersController extends DB_Handler {
    @FXML
    private Button deliveryButton;
    @FXML
    private Button doneButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Orders> table_orders;
    @FXML
    private TableColumn<Orders, String> date_table;
    @FXML
    private TableColumn<Orders, Integer> id_table;
    @FXML
    private TableColumn<Orders, Integer> klient_table;
    @FXML
    private TableColumn<Orders, String> status_table;
    @FXML
    private TableColumn<Orders, String> table_product;
    @FXML
    private TableColumn<Orders, String> table_additions;
    ObservableList<Orders> listOrd;
    private Orders orders;

    public void initialize() {
        initData();

        deliveryButton.setOnAction(event -> {
            InitWindow("EmployeesDeliveryMenu.fxml");
        });

        doneButton.setOnAction(event -> {
            try {
                orders = table_orders.getSelectionModel().getSelectedItem();
                String select = "update orders set order_status = 'done' where id_order = " + orders.getId() + ";";

                PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
                preparedStatement.execute();

            } catch (SQLIntegrityConstraintViolationException e) {
                errorAlarm();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {

            }
            initData();
        });

        deleteButton.setOnAction(event -> {
            try {
                orders = table_orders.getSelectionModel().getSelectedItem();
                String select = "delete from orders where id_order = " + orders.getId() + ";";
                System.out.println(select);

                PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
                preparedStatement.execute();

            } catch (SQLIntegrityConstraintViolationException e) {
                alertWarningDelete();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                alertProcedureOfAction();
            }
            initData();
        });
    }
    private void errorAlarm () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("You cannot delete this product if it is ordered or commented on by the customer.");
        alert.setContentText("Please, check if this product is ordered, awaiting delivery, or commented.");
        alert.showAndWait();
    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        id_table.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("id"));
        date_table.setCellValueFactory(new PropertyValueFactory<Orders, String>("date"));
        status_table.setCellValueFactory(new PropertyValueFactory<Orders, String>("order_status"));
        klient_table.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("cust_id"));
        table_product.setCellValueFactory(new PropertyValueFactory<Orders, String>("name_food"));
        table_additions.setCellValueFactory(new PropertyValueFactory<Orders, String>("name"));


        listOrd = db_handler.getOrder();
        table_orders.setItems(listOrd);
    }
}
