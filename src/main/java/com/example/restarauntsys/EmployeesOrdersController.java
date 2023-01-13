package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmployeesOrdersController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button logoutButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableColumn<?, ?> date_table;
    @FXML
    private TableColumn<?, ?> id_table;
    @FXML
    private TableColumn<?, ?> klient_table;
    @FXML
    private TableColumn<?, ?> status_table;
    @FXML
    private TableView<?> table_orders;
    @FXML
    private TableColumn<?, ?> table_product;
    @FXML
    void initialize() {

    }

}
