package com.example.restarauntsys;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Menu;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.restarauntsys.StartController.CustID;

public class CustomerCommentsController extends DB_Handler {
    @FXML
    private Button updateButton;
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

        updateButton.setOnAction(event -> {
            InitWindow("CustomerCommentsUpdate.fxml");
        });
    }
    private void addFunction() {
        try {
            String comment = commentArea.getText().trim();
            System.out.println(comment);
            menu = table_menu.getSelectionModel().getSelectedItem();
            String select = "insert into comments(comment, Customers_Users_ID_user, Menu_ID_food) " +
                    "values ('" + comment + "'," + CustID + "," + menu.getId() + ");";

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();
            information();
            commentArea.clear();


        } catch (MysqlDataTruncation e) {
            warning();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            error();
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

    private void information() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Comment has been added successfully");
        alert.setContentText("Thanks for comments of our products");
        alert.showAndWait();
    }

    private void error() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("You must choose product from table, write comment on text area and press button (comment)");
        alert.setContentText("Text area cannot is empty");
        alert.showAndWait();
    }

    private void warning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERROR");
        alert.setHeaderText("Your comment is too long, please reduce comment to 250 characters");
        alert.setContentText("Reduce comment");
        alert.showAndWait();
    }
    private void InitWindow (String window) {
        FXMLLoader fxmlLoader  = new FXMLLoader(getClass().getResource(window));
        Parent windowPane = null;

        try {
            windowPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Restaurant Application");
        stage.setScene(new Scene(windowPane));
        stage.showAndWait();
    }
}
