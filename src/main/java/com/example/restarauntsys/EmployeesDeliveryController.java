package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Delivery;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.restarauntsys.StartController.EmpID;

public class EmployeesDeliveryController extends DB_Handler{
    private Delivery delivery;
    @FXML
    private Button deliveryButton;
    @FXML
    private Button listButton;
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
        listButton.setOnAction(event -> {
            InitWindow("EmployeesDeliveryList.fxml");
        });
        deliveryButton.setOnAction(event -> {
            addDelivery();
        });
    }

    private void addDelivery(){
        try {
            delivery = table_delivery.getSelectionModel().getSelectedItem();
            String select = "insert into supplier (Delivery_ID_dilivery, Employees_Users_ID_user) values (" + delivery.getId() + ", " + EmpID + ")";
            System.out.println(select);

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();



        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    private void InitWindow(String window) {
        FXMLLoader fxmlLoader  = new FXMLLoader(getClass().getResource(window));
        Parent windowPane = null;

        try {
            windowPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Restaurant Application");
        stage.setScene(new Scene(windowPane));
        stage.showAndWait();
    }



}