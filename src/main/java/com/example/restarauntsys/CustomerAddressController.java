package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomerAddressController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    private Scene thirdScene;
    @FXML
    private Button save_button;
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
        assert save_button != null : "fx:id=\"save_button\" was not injected: check your FXML file 'CustomerAdressMenu.fxml'.";

    }

}
