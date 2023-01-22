package com.example.restarauntsys;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Customers;
import com.example.restarauntsys.tables.Employees;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartController extends DB_Handler implements Initializable {
    @FXML
    private CheckBox checkButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField nick_name;

    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    private Scene thirdScene;
    private Scene fourScene;
    public static int CustID;
    @FXML
    private Button registrationFirstButton;
    public void setThirdScene(Scene scene) {
        thirdScene = scene;
    }
    public void setFourScene(Scene scene) {
        fourScene = scene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setOnAction(event -> {
            authorization();
        });

        registrationFirstButton.setOnAction(event -> {
            InitWindow("RegistrationMenu.fxml");
        });
    }
    private void authorization() {
        String loginText = nick_name.getText().trim();
        String loginPassword = password.getText().trim();

       // nick_name.clear();
        password.clear();
        checkButton.isDisabled();

        if (checkButton.isSelected()) {
            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginEmployees(loginText, loginPassword);
            } else
                alertWarning();

        } else  {
            if (!loginText.equals("") && !loginPassword.equals("")) {
                getCustID();
                loginCustomer(loginText, loginPassword);
            } else
                alertWarning();

        }
    }

    private void alertWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Please check your username and/or password.");
        alert.setContentText("Maybe you are not registered.");
        alert.showAndWait();
    }
    private void loginCustomer(String loginText, String loginPassword) {
        DB_Handler db_handler = new DB_Handler();
        Customers customers = new Customers();
        customers.setNick_name(loginText);
        customers.setPassword(loginPassword);
        ResultSet resultSet = db_handler.getCustomer(customers);

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
            openCustomerWindow();
        } else {
            alertLOGIN();
        }

    }

    private void loginEmployees(String loginText, String loginPassword){
        DB_Handler db_handler = new DB_Handler();
        Employees employees = new Employees();
        employees.setNick_name(loginText);
        employees.setPassword(loginPassword);
        ResultSet resultSet = db_handler.getEmployees(employees);

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
            openEmployeeWindow();
        } else {
            alertLOGIN();
        }

    }
    private void alertLOGIN(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Not found information about your account!");
        alert.setContentText("Please, check your nick-name and password.");
        alert.showAndWait();
    }
    private void openCustomerWindow() {
        loginButton.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(thirdScene);
        });
    }

    private void openEmployeeWindow() {
        loginButton.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(fourScene);
        });
    }
    public int getCustID () {
        String loginText = nick_name.getText().trim();
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "select * from customers where nick_name = " + "'" + loginText + "'";
        System.out.println(selectQuery);

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            rs.next();

            CustID = Integer.parseInt(rs.getString(2));

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {

        }

        return CustID;
    }

    private void InitWindow(String window) {
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
