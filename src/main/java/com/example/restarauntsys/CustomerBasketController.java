package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Basket;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerBasketController extends DB_Handler {

    @FXML
    private Button deliveryButton;
    @FXML
    private Button logoutButton;
    private Basket basket;
    ObservableList<Basket> listB;
    @FXML
    private TableView<Basket> table_basket;
    @FXML
    private TableColumn<Basket, String> table_additions;
    @FXML
    private TableColumn<Basket, String> table_date;
    @FXML
    private TableColumn<Basket, String> table_product;
    @FXML
    private TableColumn<Basket, String> table_status;

    @FXML
    public void initialize() {
        initDataBasket();

        deliveryButton.setOnAction(event -> {
            ///initDataBasket();
        });

    }

    protected void initDataBasket() {
        DB_Handler db_handler = new DB_Handler();

        table_date.setCellValueFactory(new PropertyValueFactory<Basket, String>("date_of_order"));
        table_additions.setCellValueFactory(new PropertyValueFactory<Basket, String>("name"));
        table_product.setCellValueFactory(new PropertyValueFactory<Basket, String>("name_food"));
        table_status.setCellValueFactory(new PropertyValueFactory<Basket, String>("order_status"));

        listB = db_handler.getBasket();
        table_basket.setItems(listB);
    }


}
