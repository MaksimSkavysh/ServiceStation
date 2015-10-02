package db;

import models.CarModel;
import models.ClientCardModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksim on 01.10.2015.
 */
public class dbManager {
    private static final String URL = "jdbc:mysql://localhost:3306/service_station";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private static final String USER_ID = "USER_ID";
    private static final String FIRSTNAME = "FIRSTNAME";
    private static final String LASTNAME = "LASTNAME";
    private static final String PHONE = "PHONE";
    private static final String EMAIL = "EMAIL";
    private static final String BIRTHDAY = "BIRTHDAY";
    private static final String ADDRESS = "ADDRESS";
    private static final String MAKE = "MAKE";
    private static final String MODEL = "MODEL";
    private static final String YEAR = "YEAR";
    private static final String VIN = "VIN";

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
            preparedStatement.setString(2, user.getFirstName());
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

    public void addCar(CarModel car) {
        //INSERT INTO `service_station`.`cars` (`VIN`, `MAKE`, `MODEL`, `YEAR`, `USER_ID`) VALUES ('as223f4434wzfg', 'lada', 'kalina', '2000', '3');
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO CARS (VIN, MAKE, MODEL, YEAR, USER_ID) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, car.getVin());
            preparedStatement.setString(2, car.getMake());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, car.getYear());
            preparedStatement.setInt(5, car.getUserID());
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

    public void editUser(ClientCardModel user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE USERS SET FIRSTNAME=?, LASTNAME=?, PHONE=?, ADDRESS=?, EMAIL=?, BIRTHDAY=? WHERE USER_ID=?");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getBirthDate());
            preparedStatement.setString(7, user.getId());
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

    public List<ClientCardModel> getUsersByFIO(String firstName, String lastName){
        List<ClientCardModel> users=new ArrayList<>();
        //SELECT *  FROM service_station.users WHERE (FIRSTNAME ="vasya") AND (LASTNAME ="petrov") ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ClientCardModel user=null;
        try {
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT *  FROM USERS WHERE (FIRSTNAME =?) AND (LASTNAME =?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2,lastName);
            ResultSet userResultSet=preparedStatement.executeQuery();
            while(userResultSet.next()){
                user=new ClientCardModel();
                user.setId(userResultSet.getString(USER_ID));
                user.setFirstName(userResultSet.getString(FIRSTNAME));
                user.setLastName(userResultSet.getString(LASTNAME));
                user.setPhone(userResultSet.getString(PHONE));
                user.setEmail(userResultSet.getString(EMAIL));
                user.setBirthDate(userResultSet.getString(BIRTHDAY));
                user.setAddress(userResultSet.getString(ADDRESS));
                users.add(user);
            }
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
        return users;
    }

    public ClientCardModel getUserById(String id){
        //SELECT *  FROM service_station.users WHERE USER_ID=3
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ClientCardModel user=new ClientCardModel();
        try {
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT *  FROM USERS WHERE USER_ID=?");
            preparedStatement.setString(1, id);
            ResultSet userResultSet=preparedStatement.executeQuery();
            userResultSet.next();
            user.setId(userResultSet.getString(USER_ID));
            user.setFirstName(userResultSet.getString(FIRSTNAME));
            user.setLastName(userResultSet.getString(LASTNAME));
            user.setPhone(userResultSet.getString(PHONE));
            user.setEmail(userResultSet.getString(EMAIL));
            user.setBirthDate(userResultSet.getString(BIRTHDAY));
            user.setAddress(userResultSet.getString(ADDRESS));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
        return user;
    }

    public List<CarModel> getUserCars(String userId){
        List<CarModel> cars=new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CarModel car=null;
        try {
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM CARS WHERE USER_ID=?;");
            preparedStatement.setString(1, userId);
            ResultSet userResultSet=preparedStatement.executeQuery();
            while(userResultSet.next()){
                car=new CarModel();
                car.setMake(userResultSet.getString(MAKE));
                car.setModel(userResultSet.getString(MODEL));
                car.setYear(userResultSet.getInt(YEAR));
                car.setVin(userResultSet.getString(VIN));
                car.setUserID(userResultSet.getInt(USER_ID));
                cars.add(car);
            }
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
        return cars;
    }
}
