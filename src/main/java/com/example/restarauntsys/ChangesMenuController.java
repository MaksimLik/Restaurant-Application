package com.example.restarauntsys;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ChangesMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> id_table;

    @FXML
    private TableColumn<?, ?> kcal_table;

    @FXML
    private TableColumn<?, ?> name_table;

    @FXML
    private TableColumn<?, ?> price_table;

    @FXML
    private TableView<?> table_menu;

    @FXML
    private Button updateButton;

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'changesMenu.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'changesMenu.fxml'.";
        assert id_table != null : "fx:id=\"id_table\" was not injected: check your FXML file 'changesMenu.fxml'.";
        assert kcal_table != null : "fx:id=\"kcal_table\" was not injected: check your FXML file 'changesMenu.fxml'.";
        assert name_table != null : "fx:id=\"name_table\" was not injected: check your FXML file 'changesMenu.fxml'.";
        assert price_table != null : "fx:id=\"price_table\" was not injected: check your FXML file 'changesMenu.fxml'.";
        assert table_menu != null : "fx:id=\"table_menu\" was not injected: check your FXML file 'changesMenu.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'changesMenu.fxml'.";

    }

}
