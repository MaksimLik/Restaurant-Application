package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Additions;
import com.example.restarauntsys.tables.Orders;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerBasketController extends DB_Handler implements Initializable {

    @FXML
    private TableColumn<?, ?> date_table;

    @FXML
    private Button deliveryButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<?> table_orders;
    @FXML
    private TableColumn<?, ?> table_additions;

    @FXML
    private TableColumn<?, ?> table_product;

    @FXML
    private TableColumn<?, ?> table_status;

    private Scene thirdScene;
    public void setThirdScene(Scene scene) {
        thirdScene = scene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(thirdScene);
        });
    }


}
