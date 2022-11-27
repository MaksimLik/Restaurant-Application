package com.example.restarauntsys;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField nick_name;

    @FXML
    private TextField password;
    @FXML
    private Button loginButton;
    private Scene secondScene;
    @FXML
    private Button registrationFirstButton;

    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }
    @FXML
    void openSecondScene(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
    }

    @FXML
    void initialize() {
        loginButton.setOnAction(event -> {
            String loginText = nick_name.getText().trim();
            String loginPassword = password.getText().trim();
            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);

            else
                alertWarning();
        });

    }

    private void alertWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Please check your username and/or password.");
        alert.setContentText("Maybe you are not registered.");
        alert.showAndWait();
    }
    private void loginUser(String loginText, String loginPassword) {
        DB_Handler db_handler = new DB_Handler();
        User user = new User();
        user.setNick_name(loginText);
        user.setPassword(loginPassword);
        ResultSet resultSet = db_handler.getUser(user);

        int counter = 0;

        while(true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        if(counter >= 1){
            openNewScene("CustomerStartMenu.fxml");
        }

    }
    private void openNewScene(String window) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Restaurant customer");
        stage.setResizable(false);
        stage.showAndWait();
    }

}
