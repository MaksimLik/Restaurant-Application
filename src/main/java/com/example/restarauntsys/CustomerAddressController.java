package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Address;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.*;

import static com.example.restarauntsys.StartController.CustID;

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
    @FXML
    private Button delete_button;
    static public int amount_addr;
    @FXML
    void initialize() {
        amount();
        checkAddress();
        save_button.setOnAction(event -> {
            addAddress();
            amount();
            checkAddress();
        });
        delete_button.setOnAction(event -> {
            deleteAddress();
            amount();
            checkAddress();
        });

    }
    private void checkAddress() {
        if (amount_addr == 0) {
            questionLableNegative.setText("Your address hasn't registration");
            questionLableNegative.setTextFill(Color.web("red"));
            questionLablePositive.setVisible(false);
            delete_button.setDisable(true);
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

    private void deleteAddress() {
        try {
            String select = "delete from adress_customer where Customers_Users_ID_user = " + CustID + ";";
            System.out.println(select);

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            errorAlarm();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void errorAlarm () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("You cannot delete this address if this address waiting for delivery.");
        alert.setContentText("Please, check if this address waiting for delivery.");
        alert.showAndWait();
    }

    public void addAddress(){
        DB_Handler db_handler = new DB_Handler();
        String street = streetField.getText();
        String room = roomField.getText();
        String index = indexField.getText();

        if (!street.equals("") && !room.equals("") && !index.equals("")) {
            Address address = new Address(street, room, index);
            db_handler.registrationAddress(address);
            db_handler.registrationCustomerAddress(address);
            successRegistration();

        } else {
            warningRegistration();
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
            System.out.println(amount_addr);
            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
        }
        System.out.println(amount_addr);
        return amount_addr;
    }
    private void successRegistration(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Successfully.");
        alert.setContentText("Your address have been registered successfully.");
        alert.showAndWait();
    }

    private void warningRegistration() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("WARNING!");
        alert.setContentText("Please check information in all fields, this information is mandatory");
        alert.showAndWait();
    }
}
