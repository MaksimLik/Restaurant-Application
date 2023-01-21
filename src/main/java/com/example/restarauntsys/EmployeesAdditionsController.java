package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Additions;
import com.example.restarauntsys.tables.Menu;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeesAdditionsController extends DB_Handler implements Initializable{
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableColumn<Additions, Integer> id_table;
    @FXML
    private Button logoutButton;
    @FXML
    private TextField name_field;
    private Additions additions;
    @FXML
    private TableView<Additions> table_menu;
    @FXML
    private TableColumn<Additions, String> name_table;
    @FXML
    private TableColumn<Additions, Double> price_table;
    @FXML
    private TextField price_field;

    private Scene fourScene;
    ObservableList<Additions> listA;
    public void setFourScene(Scene scene) {
        fourScene = scene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(fourScene);
        });

        addButton.setOnAction(event -> {
            addNewProduct();
            initData();
        });

        deleteButton.setOnAction(event -> {
            deleteProduct();
            initData();
        });
    }

    private void initData() {
        DB_Handler db_handler = new DB_Handler();

        id_table.setCellValueFactory(new PropertyValueFactory<Additions, Integer>("id"));
        name_table.setCellValueFactory(new PropertyValueFactory<Additions, String>("name"));
        price_table.setCellValueFactory(new PropertyValueFactory<Additions, Double>("price"));

        listA = db_handler.getAdditions();
        table_menu.setItems(listA);
    }

    private void addNewProduct() {
        DB_Handler db_handler = new DB_Handler();

        try {
            String name = name_field.getText();
            double price = Double.parseDouble(price_field.getText());
            if (!name.equals("")) {
                Additions additions = new Additions(name, price);
                db_handler.registrationAddition(additions);
                name_field.clear();
                price_field.clear();
            } else {
                errorAlarm();
            }
        } catch (NumberFormatException e) {
            warningAlarm();
        }

    }

    private void deleteProduct() {
        ObservableList<Additions> allMenu, singleMenu;
        allMenu = table_menu.getItems();
        singleMenu = table_menu.getSelectionModel().getSelectedItems();
        singleMenu.forEach(allMenu::remove);

        try {
            additions = table_menu.getSelectionModel().getSelectedItem();
            String select = "delete from additions where ID_addition = " + additions.getId();

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void errorAlarm () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Field with name or price is empty!");
        alert.setContentText("Please, write all information about addition");
        alert.showAndWait();
    }

    private void warningAlarm () {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Please, if you can use float number you must use comma (.)");
        alert.setContentText("Please, use COMMA");
        alert.showAndWait();
    }
}
