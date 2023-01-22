package com.example.restarauntsys;

import com.example.restarauntsys.mysql.Constants;
import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Basket;
import com.example.restarauntsys.tables.Orders;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.restarauntsys.StartController.CustID;

public class CustomerBasketController extends DB_Handler {

    @FXML
    private Button deliveryButton;
    private Basket basket;
    ObservableList<Basket> listB;
    @FXML
    private TableView<Basket> table_basket;
    @FXML
    private TableColumn<Basket, String> table_additions;
    @FXML
    private TableColumn<Basket, String> table_date;
    @FXML
    private TableColumn<Basket, String> table_product;
    @FXML
    private TableColumn<Basket, String> table_status;

    @FXML
    private TableColumn<Basket, Integer> table_id;
    public int amountOrder;

    @FXML
    public void initialize() {
        initDataBasket();

        deliveryButton.setOnAction(event -> {
           // delivery.getAmount();
            addDelivery();
            //initDataBasket();
        });

    }

    protected void initDataBasket() {
        DB_Handler db_handler = new DB_Handler();

        table_id.setCellValueFactory(new PropertyValueFactory<Basket, Integer>("id_order"));
        table_additions.setCellValueFactory(new PropertyValueFactory<Basket, String>("name"));
        table_product.setCellValueFactory(new PropertyValueFactory<Basket, String>("name_food"));
        table_status.setCellValueFactory(new PropertyValueFactory<Basket, String>("order_status"));
        table_date.setCellValueFactory(new PropertyValueFactory<Basket, String>("date_of_order"));

        listB = db_handler.getBasket();
        table_basket.setItems(listB);
    }

    private void addDelivery() {
        try {
            basket = table_basket.getSelectionModel().getSelectedItem();
            String select = "insert into delivery (date, invoice, Orders_ID_order, Orders_Customers_Users_ID_user) " +
                    "values (current_time(), 'nie wymagam'," + basket.getId_order() +", " + CustID + ");";
            System.out.println(select);

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();
            information();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void information() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Delivery has been added successfully");
        alert.setContentText("Thanks for delivery in our Restaurant");
        alert.showAndWait();
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
