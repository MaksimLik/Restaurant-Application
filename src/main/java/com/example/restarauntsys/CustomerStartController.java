package com.example.restarauntsys;

import java.io.IOException;
import java.sql.*;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Address;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
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
    @FXML
    private Button updateButton;
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
            InitWindow("CustomerAddressMenu.fxml");
        });

        order_button.setOnAction(event -> {
            InitWindow("CustomerOrderMenu.fxml");
        });

        showBasketButton.setOnAction (event -> {
            //index.setText(String.valueOf(CustID));
            InitWindow("CustomerBasketMenu.fxml");
        });

        additionsButton.setOnAction(event -> {
            InitWindow("CustomerAdditionsMenu.fxml");
        });

        commentButton.setOnAction(event -> {
            InitWindow("CustomerCommentsMenu.fxml");
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

        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } 
    }

    private void InitWindow (String window) {
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
