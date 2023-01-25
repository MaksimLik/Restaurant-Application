package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Additions;
import com.example.restarauntsys.tables.Delivery;
import com.example.restarauntsys.tables.Supplier;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

import static com.example.restarauntsys.StartController.CustID;

public class EmployeesDeliveryListController extends DB_Handler {
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
        deliveryButton.setOnAction(event -> {
            try {
                supplier = table_delivery.getSelectionModel().getSelectedItem();
                String select = "delete from supplier where idSupplier = " + supplier.getID_supplier() + ";";
                System.out.println(select);

                PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
                preparedStatement.execute();

            } catch (SQLIntegrityConstraintViolationException e) {
                errorAlarm();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                warning2();
            }
            initData();
        });
    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        table_id.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("ID_supplier"));
        table_order.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("ID_delivery"));
        table_emp.setCellValueFactory(new PropertyValueFactory<Supplier, String>("nick_name"));

        listS = db_handler.getSupplier();
        table_delivery.setItems(listS);
    }
    private void errorAlarm () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("You cannot delete this product if it is ordered or commented on by the customer.");
        alert.setContentText("Please, check if this product is ordered, awaiting delivery, or commented.");
        alert.showAndWait();
    }
    private void warning2() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Please, choose product from table and click him and click button.");
        alert.setContentText("Choose product, click him and click button");
        alert.showAndWait();
    }

}