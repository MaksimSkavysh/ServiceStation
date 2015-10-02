package dao;

import db.dbManager;
import models.CarModel;
import models.ClientCardModel;
import models.OrderModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by maksim on 30.09.2015.
 */
public class UserDaoImpl implements UserDao {


    @Override
    public void addUser(ClientCardModel user) {

    }

    @Override
    public void editUser(String jsonStr) throws ParseException {
            dbManager db=new dbManager();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonStr.trim());
            ClientCardModel user=new ClientCardModel();
            user.setFirstName((String) json.get(ClientCardModel.FIRSTNAME));
            user.setLastName((String) json.get(ClientCardModel.LASTNAME));
            user.setAddress((String) json.get(ClientCardModel.ADDRESS));
            user.setBirthDate((String) json.get(ClientCardModel.BIRTHDATE));
            user.setEmail((String) json.get(ClientCardModel.EMAIL));
            user.setPhone((String) json.get(ClientCardModel.PHONE));
            user.setId((String) json.get(ClientCardModel.ID));
            db.editUser(user);

    }

    @Override
    public void editCar(String jsonStr) throws ParseException {
        dbManager db=new dbManager();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonStr.trim());
        CarModel car=new CarModel();
        car.setModel((String) json.get(CarModel.MODEL));
        car.setMake((String) json.get(CarModel.MAKE));
        car.setVin((String) json.get(CarModel.VIN));
        car.setYear(Integer.parseInt((String) json.get(CarModel.YEAR)));
        car.setUserID(Integer.parseInt((String) json.get(CarModel.USERID)));
        String oldVin=((String) json.get(CarModel.OLDVIN));
        db.editCar(car, oldVin);
    }

    @Override
    public void editOrder(String jsonStr) throws ParseException, SQLException {
        dbManager db=new dbManager();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonStr.trim());
        OrderModel order=new OrderModel();
        order.setDate((String) json.get(OrderModel.DATE));
        order.setVin((String) json.get(OrderModel.VIN));
        order.setStatus((String) json.get(OrderModel.STATUS));
        order.setAmount(Integer.parseInt((String) json.get(OrderModel.AMOUNT)));
        order.setOrderId(Integer.parseInt((String) json.get(OrderModel.ORDERID)));
        db.editOrder(order);
    }

    @Override
     public List<ClientCardModel> getUsers(String firstName, String lastName) {
        dbManager db=new dbManager();
        List<ClientCardModel> users=db.getUsersByFIO(firstName, lastName);
        return users;
    }

    @Override
    public ClientCardModel getUser(String id) {
        dbManager db=new dbManager();
        ClientCardModel user=db.getUserById(id);
        return user;
    }

    @Override
    public ClientCardModel jsonToUser(String jsonStr) {
        try {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonStr.trim());
        ClientCardModel user=new ClientCardModel();
        user.setFirstName((String) json.get(ClientCardModel.FIRSTNAME));
        user.setLastName((String)json.get(ClientCardModel.LASTNAME));
        user.setAddress((String) json.get(ClientCardModel.ADDRESS));
        user.setBirthDate((String) json.get(ClientCardModel.BIRTHDATE));
        user.setEmail((String) json.get(ClientCardModel.EMAIL));
        user.setPhone((String) json.get(ClientCardModel.PHONE));
        return user;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CarModel jsonToCar(String jsonStr) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonStr.trim());
            CarModel car=new CarModel();
            car.setVin((String) json.get(CarModel.VIN));
            car.setModel((String) json.get(CarModel.MODEL));
            car.setMake((String) json.get(CarModel.MAKE));
            car.setYear(Integer.parseInt((String)json.get(CarModel.YEAR)));
            car.setUserID(Integer.parseInt((String)json.get(CarModel.USERID)));
            return car;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addNewOrder(String jsonStr) throws ParseException, SQLException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonStr.trim());
        OrderModel order=new OrderModel();
        order.setVin((String) json.get(OrderModel.VIN));
        order.setDate((String) json.get(OrderModel.DATE));
        order.setStatus((String) json.get(OrderModel.STATUS));
        order.setAmount(Integer.parseInt((String) json.get(OrderModel.AMOUNT)));
        dbManager db=new dbManager();
        db.addOrder(order);
    }

    @Override
    public List<CarModel> getUserCars(String userId) {
        dbManager db=new dbManager();
        return db.getUserCars(userId);
    }

    @Override
    public List<OrderModel> getCarOrders(String vin) throws SQLException {
        dbManager db=new dbManager();
        return db.getCarOrders(vin);
    }
}
