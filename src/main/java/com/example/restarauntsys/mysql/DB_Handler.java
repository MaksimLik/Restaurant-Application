package com.example.restarauntsys.mysql;

import com.example.restarauntsys.tables.*;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static com.example.restarauntsys.StartController.CustID;

public class DB_Handler extends Configurations {
    Connection dbConnection;


    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connecrionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver"); //com.mysql.cj.jdbc.Driver maybe
        dbConnection = DriverManager.getConnection(connecrionString,
                dbUser, dbPass);

        return dbConnection;
    }

    public void registrationUsers (User user) {

        String insert = "INSERT INTO " + Constants.USER_TABLE + "(" +
                Constants.USER_NAME + "," + Constants.USER_SURNAME + "," + Constants.USER_NICK_NAME + "," +
                Constants.USER_PASSWORD + ")" +
                "VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getNick_name());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (MysqlDataTruncation e) {
            alertTooLongTxt();
        } catch (SQLIntegrityConstraintViolationException e) {
            alertWarningDelete();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void registrationCustomer(Customers customers) {

        String insert = "INSERT INTO " + Constants.CUSTOMERS_TABLE + "(" +
                Constants.USER_NICK_NAME + "," +
                Constants.CUSTOMER_ID_USER + ")" +
                "VALUES(?, ?)";
        System.out.println(insert);

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, customers.getNick_name());
            preparedStatement.setInt(2, Integer.parseInt(String.valueOf(customers.getID())));

            preparedStatement.executeUpdate();

        } catch (MysqlDataTruncation e) {
            alertTooLongTxt();
        } catch (SQLIntegrityConstraintViolationException e) {

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void registrationCustomerAddress(Address address) {
        String insert = "INSERT INTO " + Constants.CUSTOMER_ADDRESS + "(" +
                Constants.ID_ADDRESS + "," +
                Constants.Customer_ID + ")" +
                "VALUES(?, ?)";
        System.out.println(insert);
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, address.getIDadr());
            preparedStatement.setInt(2, CustID);

            System.out.println(CustID);

            preparedStatement.executeUpdate();

        } catch (MysqlDataTruncation e) {
            alertTooLongTxt();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void registrationAddress(Address address){
        String insert = "INSERT INTO " + Constants.MENU_ADDRESS + "(" +
                Constants.ADDRESS_STREET + "," +
                Constants.ADDRESS_ROOM + "," +
                Constants.ADDRESS_INDEX + ")" +
                "VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getRoom_number());
            preparedStatement.setString(3, address.getPostal_index());

            preparedStatement.executeUpdate();
        } catch (MysqlDataTruncation e) {
            alertTooLongTxt();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void registrationAddition(Additions additions) {
        String insert = "INSERT INTO " + Constants.ADDITIONS_TABLE + "(" +
                Constants.ADDITIONS_NAME + "," +
                Constants.ADDITIONS_PRICE + ")" +
                "VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, additions.getName());
            preparedStatement.setDouble(2, additions.getPrice());

            preparedStatement.executeUpdate();

        } catch (MysqlDataTruncation e) {
            alertTooLongTxt();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void registrationProduct(Menu menu) {
        String insert = "INSERT INTO " + Constants.MENU_TABLE + "(" +
                Constants.MENU_NAME + "," +
                Constants.MENU_DESCRIPTION + "," +
                Constants.MENU_KCAL + "," +
                Constants.MENU_PRICE + ")" +
                "VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, menu.getName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setDouble(3, menu.getKcal());
            preparedStatement.setDouble(4, menu.getPrice());

            preparedStatement.executeUpdate();

        } catch (MysqlDataTruncation e) {
            alertTooLongTxt();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getCustomer(Customers customers) {

        ResultSet resultSet = null;
        String select = "select " + Constants.USER_NICK_NAME + ", " + Constants.USER_PASSWORD + " from " +
                Constants.CUSTOMERS_TABLE + " natural join " + Constants.USER_TABLE +
                " WHERE " + Constants.USER_NICK_NAME + "=? AND " + Constants.USER_PASSWORD + "=?";

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, customers.getNick_name());
            preparedStatement.setString(2, customers.getPassword());

            resultSet = preparedStatement.executeQuery();

        } catch (MysqlDataTruncation e) {
            alertTooLongTxt();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ObservableList<Menu> getMenu() {
        ObservableList<Menu> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "call display_menu;";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                list.add(new Menu(rs.getInt("ID_food"), rs.getString("name_food"), rs.getString("description"),
                        rs.getDouble("kcal"), rs.getDouble("price")));
            }

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ObservableList<Delivery> getDelivery() {
        ObservableList<Delivery> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "call display_delivery();";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                list.add(new Delivery (rs.getInt("ID_dilivery"), rs.getString("date"), rs.getString("invoice"),
                        rs.getInt("Orders_ID_order"), rs.getString("nick_name")));
            }

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public ObservableList<Comments> getComment() {
        ObservableList<Comments> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "SELECT id_comment, name_food, comment, nick_name FROM (comments INNER JOIN menu ON comments.Menu_ID_food = menu.ID_food) " +
                "INNER JOIN Customers ON  comments.Customers_Users_ID_user = Customers.Users_ID_user;";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                list.add(new Comments(rs.getInt("id_comment"), rs.getString("nick_name"),
                        rs.getString("comment"), rs.getString("name_food")));
            }

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ObservableList<Comments> getComment2() {
        ObservableList<Comments> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "SELECT id_comment, name_food, comment FROM (comments INNER JOIN menu ON comments.Menu_ID_food = menu.ID_food) " +
                "INNER JOIN Customers ON  comments.Customers_Users_ID_user = Customers.Users_ID_user where Customers_Users_ID_user = " + CustID +";";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                list.add(new Comments(rs.getInt("id_comment"),
                        rs.getString("comment"), rs.getString("name_food")));
            }

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public ObservableList<Basket> getBasket() {
        ObservableList<Basket> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "select id_order, name, name_food, order_status, date_of_order from (orders " +
                "left outer join additions on orders.Additions_ID_addition = additions.ID_addition) " +
                "left join menu on orders.Menu_ID_food = menu.ID_food where Customers_Users_ID_user = " + CustID + ";";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                list.add(new Basket(rs.getInt("id_order"), rs.getString("name"),
                        rs.getString("name_food"), rs.getString("order_status"), rs.getString("date_of_order")));
            }

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ObservableList<Additions> getAdditions() {
        ObservableList<Additions> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "select * from additions";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                list.add(new Additions(rs.getInt("ID_addition"), rs.getString("name"),
                        rs.getDouble("price")));
            }

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ObservableList<Supplier> getSupplier() {
        ObservableList<Supplier> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "select idSupplier, Delivery_ID_dilivery, nick_name from supplier inner join employees on " +
                "Employees_Users_ID_user = Users_ID_user;";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                //columnLabel musi być taki sam jak nazwy kolumn w wyniku zapytania do BD
                list.add(new Supplier(rs.getInt("idSupplier"), rs.getInt("Delivery_ID_dilivery"),
                        rs.getString("nick_name")));
            }

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ObservableList<Orders> getOrder() {
        ObservableList<Orders> listt = FXCollections.observableArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "select id_order, date_of_order, order_status, Customers_Users_ID_user, name_food, name from " +
                "(orders left outer join additions on orders.Additions_ID_addition = additions.ID_addition) " +
                "left join menu on orders.Menu_ID_food = menu.ID_food;";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                listt.add(new Orders(rs.getInt("ID_order"), rs.getString("date_of_order"), rs.getString("order_status"),
                        rs.getInt("Customers_Users_ID_user"), rs.getString("name_food"), rs.getString("name")));
            }

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listt;
    }

    public ResultSet getEmployees(Employees employees) {
        ResultSet resultSet = null;
        String select = "select " + Constants.USER_NICK_NAME + ", " + Constants.USER_PASSWORD + " from " +
                Constants.EMPLOYEES_TABLE + " natural join " + Constants.USER_TABLE +
                " WHERE " + Constants.USER_NICK_NAME + "=? AND " + Constants.USER_PASSWORD + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, employees.getNick_name());
            preparedStatement.setString(2, employees.getPassword());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
