package com.example.restarauntsys;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Menu;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static com.example.restarauntsys.StartController.CustID;

public class CustomerOrderController extends DB_Handler implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private RadioButton deliveryButton;

    @FXML
    private TableView<Menu> table_menu;
    @FXML
    private TableColumn<Menu, Double> kcal_table;
    @FXML
    private TableColumn<Menu, Double> price_table;
    @FXML
    private TableColumn<Menu, String> name_table;
    @FXML
    private TableColumn<Menu, String> table_description;
    ObservableList<Menu> listM;
    private Menu menu;
    private Scene thirdScene;
    public void setThirdScene(Scene scene) {
        thirdScene = scene;
    }
    String loginText;
    @FXML
    void backButton(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(thirdScene);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(loginText);
        initData();
        addButton.setOnAction(event -> {
            addFunction();
        });
    }

    private void addFunction() {
        try {
            menu = table_menu.getSelectionModel().getSelectedItem();
            String select = "insert into orders (date_of_order, order_status, Customers_Users_ID_user, Menu_ID_food) " +
                    "values (current_date(), 'w procesie', " + CustID + ", " + menu.getId() + ");";
            System.out.println(select);

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        name_table.setCellValueFactory(new PropertyValueFactory<Menu, String>("name"));
        table_description.setCellValueFactory(new PropertyValueFactory<Menu, String>("description"));
        kcal_table.setCellValueFactory(new PropertyValueFactory<Menu, Double>("kcal"));
        price_table.setCellValueFactory(new PropertyValueFactory<Menu, Double>("price"));


        listM = db_handler.getMenu();
        table_menu.setItems(listM);
    }

}