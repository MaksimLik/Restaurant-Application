package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Address;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CustomerAddressController {
    @FXML
    private TextField indexField;
    @FXML
    private TextField roomField;
    @FXML
    private TextField streetField;
    @FXML
    private Button save_button;

    @FXML
    void initialize() {
        save_button.setOnAction(event -> {
            addAddress();
        });
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
