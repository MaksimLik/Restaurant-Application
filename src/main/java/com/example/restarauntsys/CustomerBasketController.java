package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Basket;
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
    public int amount;

    @FXML
    public void initialize() {
        initDataBasket();

        deliveryButton.setOnAction(event -> {
            amount();
            changesFunction();
        });

    }

    protected void changesFunction() {
        if (amount >= 1) {
            warning();
        } else {
            addDelivery();
        }
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

    private int amount() {
        ResultSet rs = null;
        Statement stmt = null;
        basket = table_basket.getSelectionModel().getSelectedItem();
        String select = "select Orders_ID_order, coalesce(sum(Orders_ID_order), 0) from delivery where Orders_ID_order = " + basket.getId_order() +
                " GROUP BY (Orders_ID_order);";
        System.out.println(select);

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(select);
            rs.next();

            amount = Integer.parseInt(rs.getString(2));
            System.out.println(amount);

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
        }
        return amount;
    }


    private void information() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Delivery has been added successfully");
        alert.setContentText("Thanks for delivery in our Restaurant");
        alert.showAndWait();
    }

    private void warning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("This order is already in the delivery service.");
        alert.setContentText("You cannot order delivery for the same order more than once.");
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
