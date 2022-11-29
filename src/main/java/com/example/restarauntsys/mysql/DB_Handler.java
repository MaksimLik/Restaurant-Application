package com.example.restarauntsys.mysql;

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

///

        ResultSet rs = null;
        Statement stmt = null;
        String selectquery = "SELECT max(id_user) FROM users";
        System.out.println("dsfsdfsdf");
        int currentUserId;
        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectquery);

            currentUserId = Integer.parseInt(String.valueOf(rs.next())); // id elegancko


        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("selectoin error");
        }

        ///




        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getNick_name());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public ResultSet getUser(User user) {

        ResultSet resultSet = null;
        String select = "SELECT * from " + Constants.USER_TABLE +
                " WHERE " + Constants.USER_NICK_NAME + "=? AND " +
                Constants.USER_PASSWORD + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getNick_name());
            preparedStatement.setString(2, user.getPassword());

            resultSet = preparedStatement.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
