package dao;

import models.CarModel;
import models.ClientCardModel;
import org.json.simple.JSONObject;

import java.util.List;

public interface UserDao {
    void addUser(ClientCardModel user);

    void editUser(ClientCardModel user);

    ClientCardModel jsonToUser(String jsonStr);

    CarModel jsonToCar(String jsonStr);

    List<ClientCardModel> getUsers(String firstName,String lastName);

    ClientCardModel getUser(String id);

    List<CarModel> getUserCars(String userId);

}
