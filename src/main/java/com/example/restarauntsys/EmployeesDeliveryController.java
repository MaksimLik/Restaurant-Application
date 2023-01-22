package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Delivery;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeesDeliveryController {
    private Delivery delivery;
    @FXML
    private Button deliveryButton;

    @FXML
    private TableView<Delivery> table_delivery;

    @FXML
    private TableColumn<Delivery, Integer> table_customer;

    @FXML
    private TableColumn<Delivery, String> table_date;

    @FXML
    private TableColumn<Delivery, Integer> table_id;

    @FXML
    private TableColumn<Delivery, String> table_invoice;

    @FXML
    private TableColumn<Delivery, Integer> table_order;
    ObservableList<Delivery> listD;
    @FXML
    void initialize() {
        initData();
    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        table_id.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("id"));
        table_date.setCellValueFactory(new PropertyValueFactory<Delivery, String>("date"));
        table_invoice.setCellValueFactory(new PropertyValueFactory<Delivery, String>("invoice"));
        table_order.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("id_order"));
        table_customer.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("nick_name"));

        listD = db_handler.getDelivery();
        table_delivery.setItems(listD);
    }



}