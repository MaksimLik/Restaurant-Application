package com.example.restarauntsys;

import com.example.restarauntsys.mysql.Alerts;
import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Customers;
import com.example.restarauntsys.tables.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class RegistrationController extends Alerts {
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
    @FXML
    public void initialize() {
        registrationButtonTwo.setOnAction(event -> {
            signUPnewUser();
        });
    }

    private void signUPnewUser(){
        DB_Handler db_handler = new DB_Handler();

        String name = signUPname.getText();
        String surname = singUPsurname.getText();
        String nick_name = signUPnick_name.getText();
        String password = signUPpassword.getText();

        if (!name.equals("") && !surname.equals("") && !nick_name.equals("") && !password.equals("")) {
            User user = new User(name, surname, password);
            db_handler.registrationUsers(user);
            signUPnewCustomer();
            alertSuccessReg();
            clearField();
        } else {
            alertWarningIsEmpty();
        }
    }

    private void signUPnewCustomer() {
        DB_Handler db_handler = new DB_Handler();

        String nick_name = signUPnick_name.getText();
        Customers customers = new Customers(nick_name);

        db_handler.registrationCustomer(customers);
    }

    private void clearField() {
        signUPname.clear();
        singUPsurname.clear();
        signUPnick_name.clear();
        signUPpassword.clear();
    }

}
