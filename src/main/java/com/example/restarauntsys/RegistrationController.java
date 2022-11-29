package com.example.restarauntsys;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
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

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logout_ButtonFirst;
    @FXML
    private TextField signUPname;

    @FXML
    private TextField signUPnick_name;

    @FXML
    private TextField signUPpassword;

    @FXML
    private TextField singUPsurname;
    @FXML
    private Button registrationButtonTwo;
    private Scene firstScene;
    public void setFirstScene(Scene scene) {
        firstScene = scene;
    }

    @FXML
    void openFirstScene(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(firstScene);
    }

    @FXML
    void initialize() {
        registrationButtonTwo.setOnAction(event -> {

            signUPnewUser();

            try {
                signUPnewCustomer();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            //   successRegistration();
        });
    }

    private void signUPnewUser(){
        DB_Handler db_handler = new DB_Handler();

        String name = signUPname.getText();
        String surname = singUPsurname.getText();
        String nick_name = signUPnick_name.getText();
        String password = signUPpassword.getText();
        User user = new User(name, surname, nick_name, password);

        db_handler.registrationUsers(user);
    }

    private void signUPnewCustomer() throws SQLException, ClassNotFoundException {
        DB_Handler db_handler = new DB_Handler();
        String nick_name = signUPnick_name.getText();
        Customers customers = new Customers(nick_name);

        db_handler.registrationCustomer(customers);
    }

    private void successRegistration(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Successfully.");
        alert.setContentText("You have been registered successfully.");
        alert.showAndWait();
    }

}
