package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Orders;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerBasketController extends DB_Handler implements Initializable {

    @FXML
    private TableColumn<?, ?> date_table;

    @FXML
    private Button deliveryButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<?, ?> status_table;

    @FXML
    private TableView<?> table_orders;

    @FXML
    private TableColumn<?, ?> table_product;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
