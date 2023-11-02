package de.ait.app.services;

import de.ait.app.model.Account;

import java.util.List;

public interface AccountService {
    public Account createAccount(String Iban, Double balance);
    public List<Account> getAllAccounts();
}