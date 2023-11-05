package de.ait.shop.repositories.impl;

import de.ait.shop.models.Account;

import de.ait.shop.repositories.AccountsRepository;


import java.util.ArrayList;
import java.util.List;

public class AccountsRepositoryListImpl implements AccountsRepository {

    private final List<Account> accounts = new ArrayList<>();
    private Long generatedId = 1L;

    @Override
    public Account findById(Long id) {
        return accounts.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
//public Account findAccountById(Long id) {
//        // Logic to find the account
//        if (found) {
//            return foundAccount;
//        } else {
//            throw new IllegalArgumentException("Account not found");
//        }
//    }
    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }

    @Override
    public Account save(Account account) {
        accounts.add(account);
        account.setId(generatedId);
        generatedId++;

        return account;
    }

    @Override
      public void deleteById(Long id) {
        accounts.removeIf(account -> account.getId().equals(id));
    }

    @Override
    public void update(Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId().equals(account.getId())) {
                accounts.set(i, account);
                return;
            }
        }
    }

    @Override
    public Account findOneByIban(String iban) {

              return accounts.stream()
                .filter(account -> account.getIban().equals(iban))
                .findFirst()
                .orElse(null);
    }

    public AccountsRepositoryListImpl() {

        save(new Account("DE123654789256474", 1236.02));
        save(new Account("DE789456123456987", 2587.56));
    }
}
