package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomerStartController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button address_button;
    @FXML
    private Button logoutButton;
    private Scene sixScene;
    private Scene firstScene;
    private Scene sevenScene;
    @FXML
    private Button order_button;
    @FXML
    private Button showBasketButton;
    public void setSixScene(Scene scene) {
        sixScene = scene;
    }
    public void setFirstScene(Scene scene) {
        firstScene = scene;
    }
    public void setSevenScene(Scene scene) {
        sevenScene = scene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(firstScene);
        });

        address_button.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(sixScene);
        });

        order_button.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(sevenScene);
        });


    }

}
