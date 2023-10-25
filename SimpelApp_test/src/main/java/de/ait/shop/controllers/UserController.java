package de.ait.shop.controllers;

import de.ait.shop.models.User;
import de.ait.shop.servis.UsersService;

import java.util.List;
import java.util.Scanner;

public class UserController {

    private final Scanner scanner;


    private final UsersService usersService;

    public UserController(Scanner scanner, UsersService usersService) {
        this.scanner = scanner;
        this.usersService = usersService;
    }

    public void  addUser(){
        System.out.println("Input email");
        String email= scanner.nextLine();

        System.out.println("Input password");
        String password=scanner.nextLine();

        User user= usersService.addUser(email,password);
        System.out.println(user);
    }

    public void getAllUsers() {
        List<User> users = usersService.getAllUsers();
        System.out.println(users);
    }
}
