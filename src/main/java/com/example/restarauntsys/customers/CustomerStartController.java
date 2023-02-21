package com.example.restarauntsys.customers;

import java.sql.*;
import com.example.restarauntsys.mysql.DB_Handler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static com.example.restarauntsys.StartController.CustID;

public class CustomerStartController extends DB_Handler {
    @FXML
    private Button deleteButton;
    @FXML
    private Button commentButton;
    @FXML
    private Button address_button;
    @FXML
    private Button logoutButton;
    private Scene firstScene;
    @FXML
    private Button order_button;
    @FXML
    private Button showBasketButton;
    @FXML
    private Button additionsButton;
    public void setFirstScene(Scene scene) {
        firstScene = scene;
    }
    @FXML
    public void initialize () {

        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(firstScene);
        });

        address_button.setOnAction(event -> {
            InitWindow("customersWindows/CustomerAddressMenu.fxml");
        });

        order_button.setOnAction(event -> {
            InitWindow("customersWindows/CustomerOrderMenu.fxml");
        });

        showBasketButton.setOnAction (event -> {
            InitWindow("customersWindows/CustomerBasketMenu.fxml");
        });

        additionsButton.setOnAction(event -> {
            InitWindow("customersWindows/CustomerAdditionsMenu.fxml");
        });

        commentButton.setOnAction(event -> {
            InitWindow("customersWindows/CustomerCommentsMenu.fxml");
        });

        deleteButton.setOnAction(event -> {
            deleteAddress();
        });

    }
    protected void deleteAddress() {
        try {
            String select = "delete from adress_customer where Customers_Users_ID_user = " + CustID + ";";
            System.out.println(select);

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            alertWarningDelete();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
