package db;

import models.CarModel;
import models.ClientCardModel;
import models.OrderModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
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

    private static final String DATE = "DATE";
    private static final String AMOUNT = "AMOUNT";
    private static final String STATUS = "STATUS";
    private static final String ORDERID = "ORDER_ID";

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

    private String getMiliseconds(){
        Calendar c = Calendar.getInstance();
        long timeInMillis = c.getTimeInMillis();
        String str = Long.toString(timeInMillis);
        return str;
    }

    private String getUserID(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        connection = dbManager.getConnection();
        preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM users");
        ResultSet userResultSet=preparedStatement.executeQuery();
        userResultSet.next();
        int count=userResultSet.getInt(1);
        return String.valueOf(count+1);
        } catch (SQLException e) {
            e.printStackTrace();
            return "0";
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


    private String getOrderID() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = dbManager.getConnection();
        preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM ORDERS");
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        String id=getMiliseconds()+resultSet.getString(1);
        preparedStatement.close();
        connection.close();
        return id;
    }

    public void addOrder(OrderModel order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO ORDERS (ORDER_ID, DATE, AMOUNT, STATUS, VIN) VALUES (?,?,?,?,?)");
        preparedStatement.setString(1, getOrderID());
            preparedStatement.setString(2, order.getDate());
            preparedStatement.setInt(3, order.getAmount());
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setString(5, order.getVin());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
    }


    public void addUser(ClientCardModel user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO USERS (USER_ID, FIRSTNAME, LASTNAME, PHONE, ADDRESS, EMAIL, BIRTHDAY) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, getUserID());
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
            preparedStatement.setString(5, car.getUserID());
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

    public void editCar(CarModel car,String oldVin){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE CARS SET VIN=?, MAKE=?, MODEL=?, YEAR=?, USER_ID=? WHERE VIN=?");
            preparedStatement.setString(1, car.getVin());
            preparedStatement.setString(2, car.getMake());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, car.getYear());
            preparedStatement.setString(5, car.getUserID());
            preparedStatement.setString(6, oldVin);
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

    public void editOrder(OrderModel order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE ORDERS SET  DATE=?, AMOUNT=?, STATUS=?, VIN=? WHERE ORDER_ID=?");
            preparedStatement.setString(1, order.getDate());
            preparedStatement.setInt(2, order.getAmount());
        preparedStatement.setString(3, order.getStatus());
            preparedStatement.setString(4, order.getVin());
            preparedStatement.setString(5, order.getOrderId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
    }


    public void deleteCar(String vin) throws SQLException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CARS WHERE VIN=?");
        preparedStatement.setString(1, vin);
        preparedStatement.executeUpdate();
    }


    public void deleteOrder(String id) throws SQLException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ORDERS WHERE ORDER_ID=?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
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
            preparedStatement.setString(2, lastName);
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
                car.setUserID(userResultSet.getString(USER_ID));
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

    public List<OrderModel> getCarOrders(String vin) throws SQLException {
        List<OrderModel> orders=new ArrayList<>();
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ORDERS WHERE VIN=?;");
        preparedStatement.setString(1, vin);
        ResultSet userResultSet=preparedStatement.executeQuery();
        OrderModel order;
        while(userResultSet.next()){
            order=new OrderModel();
            order.setOrderId(userResultSet.getString(ORDERID));
            order.setVin(userResultSet.getString(VIN));
            order.setDate(userResultSet.getString(DATE));
            order.setAmount(userResultSet.getInt(AMOUNT));
            order.setStatus(userResultSet.getString(STATUS));
            orders.add(order);
        }
        preparedStatement.close();
        connection.close();
        return orders;
    }


}
