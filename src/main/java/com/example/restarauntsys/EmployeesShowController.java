package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Basket;
import com.example.restarauntsys.tables.Comments;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeesShowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Comments, String> table_comment;

    @FXML
    private TableColumn<Comments, String> table_food;

    @FXML
    private TableColumn<Comments, Integer> table_id;

    @FXML
    private TableColumn<Comments, String> table_nick_name;

    @FXML
    private TableView<Comments> table_comments;
    private Comments comments;
    ObservableList<Comments> listC;

    @FXML
    void initialize() {
        initTable();
    }

    protected void initTable() {
        DB_Handler db_handler = new DB_Handler();

        table_id.setCellValueFactory(new PropertyValueFactory<Comments, Integer>("id"));
        table_food.setCellValueFactory(new PropertyValueFactory<Comments, String>("nick_name"));
        table_comment.setCellValueFactory(new PropertyValueFactory<Comments, String>("comment"));
        table_nick_name.setCellValueFactory(new PropertyValueFactory<Comments, String>("name_food"));

        listC = db_handler.getComment();
        table_comments.setItems(listC);
    }

}