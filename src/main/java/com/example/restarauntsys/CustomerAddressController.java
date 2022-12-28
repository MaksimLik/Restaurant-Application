package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Address;
import com.example.restarauntsys.tables.Customers;
import com.example.restarauntsys.tables.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerAddressController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField indexField;
    @FXML
    private TextField roomField;
    @FXML
    private TextField streetField;

    private Scene thirdScene;
    @FXML
    private Button save_button;
    public static int CustID;
    public void setThirdScene(Scene scene) {
        thirdScene = scene;
    }
    @FXML
    void back_button(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(thirdScene);
    }

    @FXML
    void initialize() {
        save_button.setOnAction(event -> {
            addAddress();
            addAddressCustomer();
        });

    }
    public void addAddressCustomer() {

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
       //     successRegistration();

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
