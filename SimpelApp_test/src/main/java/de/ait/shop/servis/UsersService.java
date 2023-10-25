package de.ait.shop.servis;

import de.ait.shop.models.User;

import java.util.List;

public interface UsersService {
    User addUser(String email, String password);

    List<User> getAllUsers();

}
