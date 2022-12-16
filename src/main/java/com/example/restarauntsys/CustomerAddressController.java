package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CustomerAddressController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button save_button;

    @FXML
    void back_button(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert save_button != null : "fx:id=\"save_button\" was not injected: check your FXML file 'CustomerAdressMenu.fxml'.";

    }

}
