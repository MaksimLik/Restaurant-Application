package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Additions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.restarauntsys.StartController.CustID;

public class CustomerAdditionsController extends DB_Handler implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button logoutButton;
    private Additions additions;
    @FXML
    private TableView<Additions> table_menu;
    @FXML
    private TableColumn<Additions, String> name_table;
    @FXML
    private TableColumn<Additions, Double> price_table;
    ObservableList<Additions> listA;
    private Scene thirdScene;
    public void setThirdScene(Scene scene) {
        thirdScene = scene;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(thirdScene);
        });

        addButton.setOnAction(event -> {
            addProduct();
        });
    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        name_table.setCellValueFactory(new PropertyValueFactory<Additions, String>("name"));
        price_table.setCellValueFactory(new PropertyValueFactory<Additions, Double>("price"));

        listA = db_handler.getAdditions();
        table_menu.setItems(listA);
    }

    private void addProduct() {
        try {
            additions = table_menu.getSelectionModel().getSelectedItem();
            String select = "insert into orders (date_of_order, order_status, Customers_Users_ID_user, Additions_ID_addition) " +
                    "values (current_date(), 'done', " + CustID + ", " + additions.getId() + ");";
            System.out.println(select);

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}