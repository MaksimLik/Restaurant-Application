package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Additions;
import com.example.restarauntsys.tables.Delivery;
import com.example.restarauntsys.tables.Supplier;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeesDeliveryListController {
    @FXML
    private Button deliveryButton;
    @FXML
    private TableColumn<Supplier, String> table_emp;
    @FXML
    private TableColumn<Supplier, Integer> table_id;
    @FXML
    private TableColumn<Supplier, Integer> table_order;
    @FXML
    private TableView<Supplier> table_delivery;
    private Supplier supplier;
    ObservableList<Supplier> listS;

    @FXML
    void initialize() {
        initData();

    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        table_id.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("ID_supplier"));
        table_order.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("ID_delivery"));
        table_emp.setCellValueFactory(new PropertyValueFactory<Supplier, String>("nick_name"));

        listS = db_handler.getSupplier();
        table_delivery.setItems(listS);
    }

}