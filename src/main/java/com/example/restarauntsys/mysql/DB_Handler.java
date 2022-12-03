package com.example.restarauntsys.mysql;

import com.example.restarauntsys.tables.Customers;
import com.example.restarauntsys.tables.Employees;
import com.example.restarauntsys.tables.User;

import java.sql.*;

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

            preparedStatement.close();

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

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
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
         //   preparedStatement.close(); if we use this - allowed error


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
