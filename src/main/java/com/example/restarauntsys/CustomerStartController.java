package com.example.restarauntsys;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Basket;
import com.example.restarauntsys.tables.Customers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.restarauntsys.StartController.CustID;

public class CustomerStartController extends CustomerBasketController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button address_button;
    @FXML
    private Button logoutButton;
    private Scene sixScene;
    private Scene firstScene;
    private Scene sevenScene;
    private Scene tenScene;
    private Scene twelveScene;
    @FXML
    private Text index;
    @FXML
    private Button order_button;
    @FXML
    private Button showBasketButton;
    @FXML
    private Button additionsButton;
    public void setSixScene(Scene scene) {
        sixScene = scene;
    }
    public void setFirstScene(Scene scene) {
        firstScene = scene;
    }
    public void setSevenScene(Scene scene) {
        sevenScene = scene;
    }
  /*  public void setTenScene(Scene scene) {
        tenScene = scene;
    } */
    public void setTwelveScene(Scene scene) {
        twelveScene = scene;
    }

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        System.out.println(CustID);

        logoutButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(firstScene);
        });

        address_button.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(sixScene);
        });

        order_button.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(sevenScene);
        });

        showBasketButton.setOnAction (event -> {
            //index.setText(String.valueOf(CustID));
            InitWindow("CustomerBasketMenu.fxml");
        });

        additionsButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(twelveScene);
        });

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
