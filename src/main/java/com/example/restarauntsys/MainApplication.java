package com.example.restarauntsys;

import com.example.restarauntsys.customers.CustomerStartController;
import com.example.restarauntsys.employees.EmployeesStartController;
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

        FXMLLoader thirdPageLoader  = new FXMLLoader(MainApplication.class.getResource("customersWindows/CustomerStartMenu.fxml"));
        Parent thirdPane = thirdPageLoader.load();
        Scene thirdScene = new Scene(thirdPane, 600, 400);

        FXMLLoader fourPageLoader  = new FXMLLoader(MainApplication.class.getResource("employeesWindows/EmployeesStartMenu.fxml"));
        Parent fourPane = fourPageLoader.load();
        Scene fourScene = new Scene(fourPane, 600, 400);

        StartController firstPaneController = (StartController) firstPaneLoader.getController();
        firstPaneController.setThirdScene(thirdScene);
        firstPaneController.setFourScene(fourScene);

        CustomerStartController thirdPaneController = (CustomerStartController) thirdPageLoader.getController();
        thirdPaneController.setFirstScene(firstscene);

        EmployeesStartController fourPaneController = (EmployeesStartController) fourPageLoader.getController();
        fourPaneController.setFirstScene(firstscene);

        stage.setTitle("Restaurant Application");
        stage.setScene(firstscene);
        stage.setResizable(false);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}