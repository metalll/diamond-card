package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.User;

import java.util.List;

public interface DBUser {

    void createUser(User user);
    void updateUser(User user);
    void removeUser(User user);
    User getUser(String login);
    User getUser(long id);
    List<User> getAllUsers();
    void validateUser(User user);
}
