package de.ait.shop;

import de.ait.shop.controllers.UserController;
import de.ait.shop.repositories.UsersRepository;
import de.ait.shop.repositories.impl.UsersRepositaryFieImpl;
import de.ait.shop.repositories.impl.UsersRepositoryListImpl;
import de.ait.shop.servis.impl.UsersServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersRepository usersRepositoryList= new UsersRepositoryListImpl();
        UsersRepository usersRepositoryFile = new UsersRepositaryFieImpl("users.txt");
        UsersServiceImpl registrationService = new UsersServiceImpl(usersRepositoryFile);
        UserController registraitionController = new UserController(scanner, registrationService);
               boolean isRun = true;
        while (isRun) {
            String command = scanner.nextLine();
            switch (command) {
                case "/addUser" -> registraitionController.addUser();
                case "/users" -> registraitionController.getAllUsers();
                case "/exit" -> isRun = false;
            }
        }
    }
}
