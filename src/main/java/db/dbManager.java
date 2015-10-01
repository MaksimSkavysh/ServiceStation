package db;

import models.ClientCardModel;

import java.sql.*;

/**
 * Created by maksim on 01.10.2015.
 */
public class dbManager {
    private static final String URL = "jdbc:mysql://localhost:3306/service_station";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private int getID(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        connection = dbManager.getConnection();
        preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM users");
        ResultSet userResultSet=preparedStatement.executeQuery();
        userResultSet.next();
        int count=userResultSet.getInt(1);
        return count+1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addUser(ClientCardModel user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO USERS (USER_ID, FIRSTNAME, LASTNAME, PHONE, ADDRESS, EMAIL, BIRTHDAY) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, getID());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getBirthDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
