package com.example.restarauntsys.customers;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Additions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.sql.PreparedStatement;
import java.sql.SQLException;


import static com.example.restarauntsys.StartController.CustID;

public class CustomerAdditionsController extends DB_Handler {

    @FXML
    private Button addButton;
    private Additions additions;
    @FXML
    private TableView<Additions> table_menu;
    @FXML
    private TableColumn<Additions, String> name_table;
    @FXML
    private TableColumn<Additions, Double> price_table;
    ObservableList<Additions> listA;
    @FXML
    public void initialize() {
        initData();
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

            alertSuccessReg();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            alertProcedureOfAction();
        }
    }
}