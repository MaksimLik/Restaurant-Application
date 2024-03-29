package com.example.restarauntsys.customers;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Address;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.*;

import static com.example.restarauntsys.StartController.CustID;
import static com.example.restarauntsys.tables.Address.address_id_max;
import static com.example.restarauntsys.tables.Address.address_id_max2;

public class CustomerAddressController extends DB_Handler {
    @FXML
    private Label questionLableNegative;
    @FXML
    private Label questionLablePositive;
    @FXML
    private TextField indexField;
    @FXML
    private TextField roomField;
    @FXML
    private TextField streetField;
    @FXML
    private Button save_button;
    public int amount_addr;
    @FXML
    void initialize() {
        amount();
        checkAddress();
        save_button.setOnAction(event -> {
            addAddress();
            amount();
            checkAddress();
            roomField.clear();
            indexField.clear();
            streetField.clear();
        });
    }
    private void checkAddress() {
        if (amount_addr < 1) {
            questionLableNegative.setText("Your address hasn't registration");
            questionLableNegative.setTextFill(Color.web("red"));
            questionLablePositive.setVisible(false);
            } else {
            questionLablePositive.setText("Your address has registration");
            questionLablePositive.setTextFill(Color.web("green"));
            questionLableNegative.setVisible(false);
            streetField.setDisable(true);
            roomField.setDisable(true);
            indexField.setDisable(true);
            save_button.setDisable(true);
        }
    }

    public void addAddress(){
        DB_Handler db_handler = new DB_Handler();
        String street = streetField.getText();
        String room = roomField.getText();
        String index = indexField.getText();

        if (!street.equals("") && !room.equals("") && !index.equals("")) {
            Address address = new Address(street, room, index);
            address.getIDadr();
            db_handler.registrationAddress(address);
            address.getIDadr2();
                if(address_id_max != address_id_max2)   {
                db_handler.registrationCustomerAddress(address);
                alertSuccessReg();
            }

        } else {
            alertWarningIsEmpty();
        }
    }

    private int amount(){
        ResultSet rs = null;
        Statement stmt = null;
        String select = "select * from adress_customer where Customers_Users_ID_user = " + CustID + ";";
        System.out.println(select);

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(select);
            rs.next();

            amount_addr = Integer.parseInt(rs.getString(2));
            rs.close();
            stmt.close();


        } catch (SQLException | ClassNotFoundException e) {
        }
        return amount_addr;
    }
}
