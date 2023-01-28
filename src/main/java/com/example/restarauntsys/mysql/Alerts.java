package com.example.restarauntsys.mysql;

import com.example.restarauntsys.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Alerts {
    protected  Alert alert;
    protected void alertWarningIsEmpty() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Please fill all fields and use COMMA.");
        alert.setContentText("All fields are mandatory and use COMMA, don't use ',' when you write decimal numbers!");
        alert.showAndWait();
    }

    protected void alertWarningDelete() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("You cannot delete or update this product if it is ordered or commented by the customer.");
        alert.setContentText("Please, check if this product is ordered, awaiting delivery, or commented. You must" +
                "delete this product from comments or delivery and only then delete from menu.");
        alert.showAndWait();
    }

    protected void alertProcedureOfAction() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Please, choose product from table and click him and click button.");
        alert.setContentText("Choose product, click him and click button");
        alert.showAndWait();
    }

    protected void alertTooLongTxt() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERROR");
        alert.setHeaderText("Your text in field is too long!");
        alert.setContentText("Please, reduce your text to normal amount of characters");
        alert.showAndWait();
    }
    protected void alertSuccessReg() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Successfully.");
        alert.setContentText("Your information have been registered successfully. " +
                "Thanks for delivery in our Restaurant");
        alert.showAndWait();
    }

    protected void alertSimilarProduct() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("This order is already.");
        alert.setContentText("You cannot add each-other function for the same order more than once.");
        alert.showAndWait();
    }

    protected void alertNotFoundInformation() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Not found information about You!");
        alert.setContentText("Please, check your nick-name and password. Maybe you are not registered.");
        alert.showAndWait();
    }

    protected void InitWindow(String window) {
        //żeby program wiedział skąd ma probrać te dane - to najlepiej wpisać główną klasę
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(window));
        //    FXMLLoader fxmlLoader  = new FXMLLoader(getClass().getResource(window));
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
