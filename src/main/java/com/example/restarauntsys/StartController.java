package com.example.restarauntsys;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Customers;
import com.example.restarauntsys.tables.Employees;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Scene secondScene;
    private Scene thirdScene;
    private Scene fourScene;
    public static int CustID;
    @FXML
    private Button registrationFirstButton;
    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }
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

            System.out.println(getCustID());
        });

        registrationFirstButton.setOnAction(event -> {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(secondScene);
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
            throw new RuntimeException(e);
        }

        return CustID;
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
            //openNewScene("CustomerStartMenu.fxml");
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
            //openNewScene("EmployeesStartMenu.fxml");
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

/*    private void openNewScene(String window) {

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
    } */
}
