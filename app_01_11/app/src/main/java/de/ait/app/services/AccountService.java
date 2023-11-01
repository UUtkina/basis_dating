package de.ait.app.services;

import de.ait.app.model.Account;
import de.ait.app.model.User;

import java.util.List;

public interface AccountService {
    public void createAccount(String Iban, Double balance);
    public List<Account> getAllAccounts();
}