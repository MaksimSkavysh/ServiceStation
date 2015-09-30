package dao;

import models.ClientCardModel;

import java.util.List;

/**
 * Created by maksim on 30.09.2015.
 */
public interface UserDao {
    void addUser(ClientCardModel user);

    void editUser(ClientCardModel user);

    List<ClientCardModel> getUsers(String firstName,String lastName);
}
