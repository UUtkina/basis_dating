package de.ait.shop.repositories.impl;

import de.ait.shop.models.User;
import de.ait.shop.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryListImpl implements UsersRepository {

    private final List<User> users = new ArrayList<>();
    private Long generatedId = 1L;

    @Override
    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User save(User user) {
        users.add(user);
        user.setId(generatedId);
        generatedId++;

        return user;
    }

    @Override
      public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                return;
            }
        }
    }

    @Override
    public User findOneByEmail(String email) {

        // }
//        for(User user:users){
//            if(user.getEmail().equals(email)){
//
//                return user;
//            }
//        }
//
//
//        return null;
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public UsersRepositoryListImpl() {

        save(new User("user1@example.com", "password123"));
        save(new User("user2@example.com", "password123"));
    }
}
