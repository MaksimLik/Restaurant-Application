package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Menu;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.restarauntsys.StartController.CustID;

public class CustomerCommentsController extends DB_Handler {

    @FXML
    private TextArea commentArea;
    @FXML
    private Button commentButton;
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

    @FXML
    public void initialize() {
        initData();

        commentButton.setOnAction(event -> {
            addFunction();
        });
    }

    private void addFunction() {
        try {
            String comment = commentArea.getText();
            System.out.println(comment);
            menu = table_menu.getSelectionModel().getSelectedItem();
            String select = "insert into comments(comment, Customers_Users_ID_user, Menu_ID_food) " +
                    "values ('" + comment + "'," + CustID + "," + menu.getId() + ");";

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
