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

        FXMLLoader thirdPageLoader  = new FXMLLoader(MainApplication.class.getResource("CustomerStartMenu.fxml"));
        Parent thirdPane = thirdPageLoader.load();
        Scene thirdScene = new Scene(thirdPane, 600, 400); //

        FXMLLoader fourPageLoader  = new FXMLLoader(MainApplication.class.getResource("EmployeesStartMenu.fxml"));
        Parent fourPane = fourPageLoader.load();
        Scene fourScene = new Scene(fourPane, 600, 400);

        FXMLLoader fivePageLoader  = new FXMLLoader(MainApplication.class.getResource("changesMenu.fxml"));
        Parent fivePane = fivePageLoader.load();
        Scene fiveScene = new Scene(fivePane, 900, 550);

        FXMLLoader sixPageLoader  = new FXMLLoader(MainApplication.class.getResource("CustomerAddressMenu.fxml"));
        Parent sixPane = sixPageLoader.load();
        Scene sixScene = new Scene(sixPane, 619, 427);

        FXMLLoader sevenPageLoader  = new FXMLLoader(MainApplication.class.getResource("CustomerOrderMenu.fxml"));
        Parent sevenPane = sevenPageLoader.load();
        Scene sevenScene = new Scene(sevenPane, 850, 600);

        FXMLLoader eightPageLoader  = new FXMLLoader(MainApplication.class.getResource("EmployeesOrdersMenu.fxml"));
        Parent eightPane = eightPageLoader.load();
        Scene eightScene = new Scene(eightPane, 900, 550);

        FXMLLoader ninePageLoader  = new FXMLLoader(MainApplication.class.getResource("EmployeesAdditionsMenu.fxml"));
        Parent ninePane = ninePageLoader.load();
        Scene nineScene = new Scene(ninePane, 520, 550);

        FXMLLoader tenPageLoader  = new FXMLLoader(MainApplication.class.getResource("CustomerBasketMenu.fxml"));
        Parent tenPane = tenPageLoader.load();
        Scene tenScene = new Scene(tenPane, 445, 425);

        StartController firstPaneController = (StartController) firstPaneLoader.getController();
        firstPaneController.setSecondScene(secondScene);
        firstPaneController.setThirdScene(thirdScene);
        firstPaneController.setFourScene(fourScene);

        RegistrationController secondPaneController = (RegistrationController) secondPageLoader.getController();
        secondPaneController.setFirstScene(firstscene);

        CustomerStartController thirdPaneController = (CustomerStartController) thirdPageLoader.getController();
        thirdPaneController.setFirstScene(firstscene);
        thirdPaneController.setSixScene(sixScene);
        thirdPaneController.setSevenScene(sevenScene);
        thirdPaneController.setTenScene(tenScene);

        EmployeesStartController fourPaneController = (EmployeesStartController) fourPageLoader.getController();
        fourPaneController.setFiveScene(fiveScene);
        fourPaneController.setFirstScene(firstscene);
        fourPaneController.setNineScene(eightScene);
        fourPaneController.setTenScene(nineScene);


        CustomerAddressController fivePaneController = (CustomerAddressController) sixPageLoader.getController();
        fivePaneController.setThirdScene(thirdScene);

        CustomerOrderController sixPaneController = (CustomerOrderController) sevenPageLoader.getController();
        sixPaneController.setThirdScene(thirdScene);

        ChangesMenuController eightPaneController = (ChangesMenuController) fivePageLoader.getController();
        eightPaneController.setFourScene(fourScene);

        EmployeesOrdersController ninePaneController = (EmployeesOrdersController) eightPageLoader.getController();
        ninePaneController.setFourScene(fourScene);

        EmployeesAdditionsController tenPaneController = (EmployeesAdditionsController) ninePageLoader.getController();
        tenPaneController.setFourScene(fourScene);

        CustomerBasketController elevenPaneContoller = (CustomerBasketController) tenPageLoader.getController();
        elevenPaneContoller.setThirdScene(thirdScene);


        stage.setTitle("Restaurant Application");
        stage.setScene(firstscene);
        stage.setResizable(false);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}