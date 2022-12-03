package com.example.restarauntsys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader firstPaneLoader = new FXMLLoader(MainApplication.class.getResource("StartMenu.fxml"));
        Parent firstPane = firstPaneLoader.load();
        Scene firstscene = new Scene(firstPane, 600, 400);

        FXMLLoader secondPageLoader  = new FXMLLoader(MainApplication.class.getResource("RegistrationMenu.fxml"));
        Parent secondPane = secondPageLoader.load();
        Scene secondScene = new Scene(secondPane, 600, 400);

      /*  FXMLLoader thirdPageLoader  = new FXMLLoader(MainApplication.class.getResource("CustomersStartMenu.fxml"));
        Parent thirdPane = secondPageLoader.load();
        Scene thirdScene = new Scene(secondPane, 600, 400); */

        StartController firstPaneController = (StartController) firstPaneLoader.getController();
        firstPaneController.setSecondScene(secondScene);

        RegistrationController secondPaneController = (RegistrationController) secondPageLoader.getController();
        secondPaneController.setFirstScene(firstscene);



        stage.setTitle("Restaurant Application");
        stage.setScene(firstscene);
        stage.setResizable(false);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}