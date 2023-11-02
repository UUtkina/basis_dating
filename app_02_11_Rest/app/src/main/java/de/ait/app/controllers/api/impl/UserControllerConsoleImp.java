package de.ait.app.controllers.api.impl;

import de.ait.app.controllers.CrudeController;
import de.ait.app.model.User;
import de.ait.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class UserControllerConsoleImp implements CrudeController<User> {
    Scanner scanner;
    private UsersService service;


    public UserControllerConsoleImp(Scanner scanner, UsersService service) {
        this.scanner = scanner;
        this.service = service;
    }

    @Override
    public void create() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        try {
            service.createUser(name, email);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        return service.getAllUsers();
    }

    @Override
    public void printAll() {
        List<User> allUsers = service.getAllUsers();
        if(allUsers.isEmpty()){
            System.out.println("There is no users or data is unavailable");
        } else {
                allUsers.forEach(s -> System.out.println(s));
        }
    }

}
