package de.ait.app.repositories.impl;

import de.ait.app.model.User;
import de.ait.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("userRepositoryList")
@Qualifier("userRepositoryList")
@Lazy
public class UserRepositoryListImpl implements UserRepository {
    private  Long  currentID=1L;
    private final List<User> users = new ArrayList<>();

    {
        System.out.println("List repository start");
    }

    @Override
    public void save(User user) {
        System.out.println("save to list repo");
        users.add(user);
        user.setId(currentID++);
    }

    @Override
    public User findByID(Long id) {
        return users.stream()
                .filter(user->user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public void update(User user) {
    }

    @Override
    public User findByEmail(String email) {
        return users.stream()
                .filter(user->user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
