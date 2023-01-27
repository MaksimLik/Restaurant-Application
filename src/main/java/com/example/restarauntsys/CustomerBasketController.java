package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Basket;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.restarauntsys.StartController.CustID;

public class CustomerBasketController extends DB_Handler {

    @FXML
    private Button deliveryButton;
    private Basket basket;
    @FXML
    private Label fullPrice;
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
    @FXML
    private ChoiceBox<String> choiceBox;
    private String choice [] = {"NOT needed", "Needed"};
    public int amount;
    public int fullCosts;
    @FXML
    public void initialize() {
        initDataBasket();
        fullCost();
        checkBasket();
        choiceBox.getItems().addAll(choice);
        deliveryButton.setOnAction(event -> {
            try{
                finalFuction();
            } catch (NullPointerException e) {
                alertProcedureOfAction();
            }
        });
    }


    private void checkBasket() {
        if (fullCosts < 1) {
            fullPrice.setText("You don't have a product in shop-basket");
            fullPrice.setTextFill(Color.web("red"));
        } else {
            fullPrice.setText(String.valueOf(fullCosts) + "[$]");
            fullPrice.setTextFill(Color.web("green"));
        }
    }
    protected void finalFuction() {
        amount();
        if(choiceBox.getValue().equals("NOT needed")){
            if (amount >= 1) {
                alertSimilarProduct();
            } else {
                addDelivery();
            }

        } else if (choiceBox.getValue().equals("Needed")) {
            if (amount >= 1) {
                alertSimilarProduct();
            } else {
                addDelivery2();
            }

        } else {
            alertProcedureOfAction();
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

    private int fullCost() {
        ResultSet rs = null;
        Statement stmt = null;

        String select = "select how_much(" + CustID + ");";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(select);
            rs.next();

            fullCosts = Integer.parseInt(rs.getString(1));

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {

        } catch (NumberFormatException e) {
          //  alertWarningIsEmpty();
        }
    return fullCosts;
    }

    private void addDelivery() {
        try {
            basket = table_basket.getSelectionModel().getSelectedItem();
            String select = "insert into delivery (date, invoice, Orders_ID_order, Orders_Customers_Users_ID_user) " +
                    "values (current_time(), 'NOT needed'," + basket.getId_order() +", " + CustID + ");";

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

            alertSuccessReg();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addDelivery2() {
        try {
            basket = table_basket.getSelectionModel().getSelectedItem();
            String select = "insert into delivery (date, invoice, Orders_ID_order, Orders_Customers_Users_ID_user) " +
                    "values (current_time(), 'Needed'," + basket.getId_order() +", " + CustID + ");";

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

            alertSuccessReg();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int amount() {
        ResultSet rs = null;
        Statement stmt = null;
        basket = table_basket.getSelectionModel().getSelectedItem();
        String select = "select Orders_ID_order, coalesce(sum(Orders_ID_order), 0) from delivery where Orders_ID_order = " + basket.getId_order() +
                " and Orders_Customers_Users_ID_user = " + CustID +
                " GROUP BY (Orders_ID_order);";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(select);
            rs.next();

            amount = Integer.parseInt(rs.getString(2));

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {

        }
        return amount;
    }

}
