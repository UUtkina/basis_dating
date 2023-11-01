package de.ait.app.services;

import java.util.List;
import de.ait.app.model.User;

public interface UsersService {
    public void createUser(String name, String email);
    public List<User> getAllUsers();
}
