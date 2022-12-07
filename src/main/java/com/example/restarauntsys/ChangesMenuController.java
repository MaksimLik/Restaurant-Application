package com.example.restarauntsys;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ChangesMenuController extends DB_Handler implements Initializable {
    @FXML
    private VBox item_list;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField price_field;
    @FXML
    private TextField name_field;
    @FXML
    private TextField kcal_field;
    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;
    @FXML
    private TableView<Menu> table_menu;
    @FXML
    private TableColumn<Menu, Integer> id_table;
    @FXML
    private TableColumn<Menu, Double> kcal_table;
    @FXML
    private TableColumn<Menu, Double> price_table;
    @FXML
    private TableColumn<Menu, String> name_table;


    ObservableList<Menu> listM;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        addButton.setOnAction(event -> {
            addNewProduct();
            initData();
            name_field.clear();
            price_field.clear();
            kcal_field.clear();
        });
    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        id_table.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("id"));
        name_table.setCellValueFactory(new PropertyValueFactory<Menu, String>("name"));
        kcal_table.setCellValueFactory(new PropertyValueFactory<Menu, Double>("kcal"));
        price_table.setCellValueFactory(new PropertyValueFactory<Menu, Double>("price"));


        listM = db_handler.getMenu();
        table_menu.setItems(listM);
    }

 /*   @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            addNewProduct();
        });

    } */

    private void informationTable () {
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "select * from menu";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
                        rs.getFloat(3) + " " +  rs.getFloat(4));
            }
            System.out.println("chuj");


            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addNewProduct() {
        DB_Handler db_handler = new DB_Handler();

        try {
            String name = name_field.getText();
            double kcal = Double.parseDouble(kcal_field.getText());
            double price = Double.parseDouble(price_field.getText());
            if (!name.equals("")) {
                Menu menu = new Menu(name, kcal, price);
                db_handler.registrationProduct(menu);
            } else {
                errorAlarm();
            }
        } catch (NumberFormatException e) {
            errorAlarm();
        }

    }

    private void errorAlarm () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Field with name, price or kcal is empty!");
        alert.setContentText("Please, write all information about product");
        alert.showAndWait();
    }

}
