package com.example.restarauntsys.employees;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Comments;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeesShowController extends DB_Handler {

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

        deleteButton.setOnAction(event -> {
            try {
                comments = table_comments.getSelectionModel().getSelectedItem();
                String select = "delete from comments where id_comment = " + comments.getId() + ";";
                System.out.println(select);

                PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
                preparedStatement.execute();

            } catch (SQLIntegrityConstraintViolationException e) {
                alertWarningDelete();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                alertProcedureOfAction();
            }
            initTable();
        });
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