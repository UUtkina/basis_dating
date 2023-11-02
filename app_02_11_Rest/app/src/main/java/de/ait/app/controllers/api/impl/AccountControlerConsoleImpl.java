package de.ait.app.controllers.api.impl;

import de.ait.app.controllers.CrudeController;
import de.ait.app.model.Account;
import de.ait.app.services.AccountService;

import java.util.List;
import java.util.Scanner;

public class AccountControlerConsoleImpl implements CrudeController<Account> {
    Scanner scanner;
    private AccountService service;

    public AccountControlerConsoleImpl(Scanner scanner, AccountService service) {
        this.scanner = scanner;
        this.service = service;
    }

    @Override
    public void create() {
        System.out.print("Enter Iban: ");
        String iban = scanner.nextLine();

        System.out.print("Enter balance: ");
        Double balance = Double.parseDouble(scanner.nextLine());

        try {
            service.createAccount(iban, balance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public void printAll() {
        List<Account> allAccounts = service.getAllAccounts();
        if (allAccounts.isEmpty()) {
            System.out.println("There is no users or data is unavailable");
        } else {
            allAccounts.forEach(s -> System.out.println(s));
        }
    }

}

