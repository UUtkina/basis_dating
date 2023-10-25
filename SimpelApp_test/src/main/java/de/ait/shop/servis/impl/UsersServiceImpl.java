package de.ait.shop.servis.impl;

import de.ait.shop.models.User;
import de.ait.shop.repositories.UsersRepository;
import de.ait.shop.servis.UsersService;

import java.util.List;

public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User addUser(String email, String password) {

        if (email == null || email.equals("") || email.equals(" ")) {//валидируем еmail
            throw new IllegalArgumentException("Email ist nicht correct");
        }
        if (password == null || password.equals("") || password.equals(" ")) {//валидируем пароль
            throw new IllegalArgumentException("Password ist nicht correct");

        }
        User existedUser=usersRepository.findOneByEmail(email);//находим пользователя по email

        if(existedUser!=null){
            throw new IllegalArgumentException("Email ist duble");
        }
        User user = new User(email, password);

        usersRepository.save(user);

        return user;
    }
    public List<User>getAllUsers(){
        return usersRepository.findAll();
    }
}