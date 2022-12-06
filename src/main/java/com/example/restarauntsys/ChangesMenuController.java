package com.example.restarauntsys;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.Constants;
import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Menu;
import com.example.restarauntsys.tables.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChangesMenuController extends DB_Handler {
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
    private TableColumn<Menu, Float> kcal_table;

    @FXML
    private TableColumn<Menu, String> name_table;

    @FXML
    private TableColumn<Menu, Float> price_table;

    ObservableList<Menu> list = FXCollections.observableArrayList();

    ArrayList<Menu> lista = new ArrayList<Menu>();
    @FXML
    public void initialize() {
        initData();
//        informationTable();
//        System.out.println(list);
//        id_table.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("id"));
//        name_table.setCellValueFactory(new PropertyValueFactory<Menu, String>("name"));
//        kcal_table.setCellValueFactory(new PropertyValueFactory<Menu, Float>("kcal"));
//        price_table.setCellValueFactory(new PropertyValueFactory<Menu, Float>("price"));
//
//        table_menu.setItems(list);
    }

    private void initData() {

        lista.add(new Menu(1,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(2,"Amanoc", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(3,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(1,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(2,"Amanoc", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(3,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(1,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(2,"Amanoc", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(3,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(1,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(2,"Amanoc", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(3,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(1,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(2,"Amanoc", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(3,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(1,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(2,"Amanoc", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(3,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(1,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(2,"Amanoc", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(3,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(1,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(2,"Amanoc", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(3,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(1,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(2,"Amanoc", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(3,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(1,"Bambook", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(2,"Amanoc", (float) 12.12345, (float) 13.3));
        lista.add(new Menu(3,"Bambook", (float) 12.12345, (float) 13.3));
        lista.forEach(item -> {
            Label id = new Label(item.getId().toString());
            Label nazwa = new Label(item.getName().toString());
            HBox hbox = new HBox();
            hbox.getChildren().add(id);
            hbox.getChildren().add(nazwa);

            item_list.getChildren().add(hbox);
        });
       // lista.a


//        item_list.getChildren().add(b);
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
            float kcal = Float.parseFloat(kcal_field.getText());
            float price = Float.parseFloat(price_field.getText());
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
