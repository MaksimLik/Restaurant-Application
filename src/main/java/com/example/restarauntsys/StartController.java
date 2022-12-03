package com.example.restarauntsys;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.example.restarauntsys.mysql.Constants;
import com.example.restarauntsys.mysql.DB_Handler;
import com.example.restarauntsys.tables.Customers;
import com.example.restarauntsys.tables.Employees;
import com.example.restarauntsys.tables.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartController extends DB_Handler{
    @FXML
    private CheckBox checkButton;

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
    private String chk_nick;

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
          //  findEmployees();
            chkmethod ();
       /*   String loginText = nick_name.getText().trim();
           String loginPassword = password.getText().trim();

           System.out.println(loginText +"  "+ loginPassword);

           if(!loginText.equals("") && !loginPassword.equals(""))
               loginCustomer(loginText, loginPassword);

           else
               alertWarning(); */
        });

    }

    public void chkmethod (){
        String loginText = nick_name.getText().trim();
        String loginPassword = password.getText().trim();

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
    public String findEmployees() {
        if (checkButton.isSelected()){
            ResultSet rs = null;
            Statement stmt = null;
            String loginText2 = nick_name.getText().trim();
            String selectQuery = "select * from employees where nick_name ='" + loginText2 +"'";
            System.out.println(selectQuery);
            try {
                stmt = getDbConnection().createStatement();
                rs = stmt.executeQuery(selectQuery);
                rs.next();

                chk_nick = (rs.getString(1));

                rs.close();
                stmt.close();

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            System.out.println(chk_nick);
        }
        return chk_nick;
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
   //     User user = new User();
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
            openNewScene("CustomerStartMenu.fxml");
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
            openNewScene("EmployeesStartMenu.fxml");
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
