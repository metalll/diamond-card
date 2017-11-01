package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.User;

import java.util.List;

public interface DBUser {

    void createUser(User user);
    void updateUser(User user);
    void removeUser(User user);
    User getUser(String login);
    User getUser(long id);
    User getUserWithCard(String cashbackCard);
    List<User> getAllUsers();
    void validateUser(User user);
}
