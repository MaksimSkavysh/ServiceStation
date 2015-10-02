package dao;

import models.CarModel;
import models.ClientCardModel;
import models.OrderModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void addUser(ClientCardModel user);

    void editUser(String jsonStr) throws ParseException;

    void editCar(String jsonStr) throws ParseException;

    ClientCardModel jsonToUser(String jsonStr);

    CarModel jsonToCar(String jsonStr);

    List<ClientCardModel> getUsers(String firstName,String lastName);

    ClientCardModel getUser(String id);

    List<CarModel> getUserCars(String userId);

    List<OrderModel> getCarOrders(String vin) throws SQLException;

}
