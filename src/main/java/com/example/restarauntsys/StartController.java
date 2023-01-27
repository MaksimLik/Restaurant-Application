package com.example.restarauntsys;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Customers;
import com.example.restarauntsys.tables.Employees;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class StartController extends DB_Handler {
    @FXML
    private CheckBox checkButton;
    @FXML
    private TextField nick_name;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    private Scene thirdScene;
    private Scene fourScene;
    public static int CustID;
    public static int EmpID;
    @FXML
    private Button registrationFirstButton;
    public void setThirdScene(Scene scene) {
        thirdScene = scene;
    }
    public void setFourScene(Scene scene) {
        fourScene = scene;
    }

    @FXML
    public void initialize() {
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

        password.clear();
        checkButton.isDisabled();

        if (checkButton.isSelected()) {
            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginEmployees(loginText, loginPassword);
                getEmpID();
            } else
                alertNotFoundInformation();

        } else  {
            if (!loginText.equals("") && !loginPassword.equals("")) {
                getCustID();
                loginCustomer(loginText, loginPassword);
            } else
                alertNotFoundInformation();

        }
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
            alertNotFoundInformation();
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
            alertNotFoundInformation();
        }
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

    public int getEmpID() {
        String loginText = nick_name.getText().trim();
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "select * from employees where nick_name = " + "'" + loginText + "'";
        System.out.println(selectQuery);

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            rs.next();

            EmpID = Integer.parseInt(rs.getString(3));

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {

        }

        return EmpID;
    }
}
