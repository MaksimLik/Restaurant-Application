package com.example.restarauntsys.customers;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Comments;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class CustomerComUPController extends DB_Handler {
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private TableColumn<Comments, String> table_comment;
    @FXML
    private TableColumn<Comments, String> table_food;
    @FXML
    private TableColumn<Comments, Integer> table_id;
    @FXML
    private TableView<Comments> table_comments;
    private Comments comments;
    ObservableList<Comments> listC;
    @FXML
    private TextArea commentArea;
    @FXML
    void initialize() {
        initTable();

        deleteButton.setOnAction(event -> {
            deleteComment();
            initTable();
        });

        updateButton.setOnAction(event -> {
            updateComment();
            initTable();
        });
    }

    protected void initTable() {
        DB_Handler db_handler = new DB_Handler();

        table_id.setCellValueFactory(new PropertyValueFactory<Comments, Integer>("id"));
        table_comment.setCellValueFactory(new PropertyValueFactory<Comments, String>("name_food"));
        table_food.setCellValueFactory(new PropertyValueFactory<Comments, String>("comment"));

        listC = db_handler.getComment2();
        table_comments.setItems(listC);
    }

    private void deleteComment() {
        try {
            comments = table_comments.getSelectionModel().getSelectedItem();
            String select = "delete from comments where id_comment = " + comments.getId() + ";";
            System.out.println(select);

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            alertWarningDelete();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            alertProcedureOfAction();
        }

    }

    private void updateComment() {
        String comment = commentArea.getText().trim();
        if(!comment.equals("")) {
            try {
                comments = table_comments.getSelectionModel().getSelectedItem();
                String select = "UPDATE comments set comment = '" + commentArea.getText() + "' where id_comment = " + comments.getId() + ";";
                System.out.println(select);

                PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
                preparedStatement.execute();

                preparedStatement.close();

            } catch (SQLIntegrityConstraintViolationException e) {
                alertWarningDelete();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                alertProcedureOfAction();
            }
        } else {
            alertWarningIsEmpty();
        }
    }
}