package dao;

import models.ClientCardModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksim on 30.09.2015.
 */
public class UserDaoImpl implements UserDao {


    @Override
    public void addUser(ClientCardModel user) {

    }

    @Override
    public void editUser(ClientCardModel user) {

    }

    @Override
    public List<ClientCardModel> getUsers(String firstName, String lastName) {
        List<ClientCardModel> users=new ArrayList<>();
        users.add(new ClientCardModel("vasya","sidorov","30.06.1995","minsk","vas@gmail.com","+375291186106"));
        users.add(new ClientCardModel("1vasya","1sidorov","31.06.1995","1minsk","1vas@gmail.com","+175291186106"));
        users.add(new ClientCardModel("2vasya","2sidorov","32.06.1995","2minsk","2vas@gmail.com","+275291186106"));
        return users;
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
}
