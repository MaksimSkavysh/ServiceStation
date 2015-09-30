package dao;

import models.ClientCardModel;

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
}
