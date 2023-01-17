package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Menu;
import com.example.restarauntsys.tables.Orders;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EmployeesOrdersController extends DB_Handler implements Initializable {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button logoutButton;
    @FXML
    private Button deliveryButton;
    @FXML
    private Button doneButton;
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

    ObservableList<Orders> listOrd;
    private Orders orders;
    private Scene fourScene;
    public void setFourScene(Scene scene) {
        fourScene = scene;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(fourScene);
        });

        deliveryButton.setOnAction(event -> {
            //TODO
        });

        doneButton.setOnAction(event -> {
            //TODO
        });
    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        id_table.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("id"));
        date_table.setCellValueFactory(new PropertyValueFactory<Orders, String>("date"));
        status_table.setCellValueFactory(new PropertyValueFactory<Orders, String>("order_status"));
        klient_table.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("cust_id"));
        table_product.setCellValueFactory(new PropertyValueFactory<Orders, String>("name_food"));


        listOrd = db_handler.getOrder();
        table_orders.setItems(listOrd);
    }
}
