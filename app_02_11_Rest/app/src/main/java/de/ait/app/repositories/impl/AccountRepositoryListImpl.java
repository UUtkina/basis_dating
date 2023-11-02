package de.ait.app.repositories.impl;

import de.ait.app.model.Account;
import de.ait.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("accountRepositoryList")
@Qualifier("accountRepositoryList")

public class AccountRepositoryListImpl implements AccountRepository {
    private  Long  currentID=1L;
    private final List<Account> accounts = new ArrayList<>();

    {
       List<Account> data=List.of(
               new Account( null, "DE123665478899200",5564.2),
               new Account( null, "DE123665478899201",5064.2),
               new Account( null, "DE123665478899202",5004.7),
               new Account( null, "DE123665478899203",3560.4),
               new Account( null, "DE123665478899204",2564.5)
       );
        accounts.forEach((this::save));
    }


    @Override
    public void save(Account account) {
        System.out.println("save to list repo");
        accounts.add(account);
        account.setId(currentID++);
    }

    @Override
    public Account findByID(Long id) {
        return accounts.stream()
                .filter(account->account.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }

    @Override
    public void deleteById(Long id) {
        accounts.removeIf(account -> account.getId().equals(id));
    }


    @Override
    public void update(Account item) {

    }


    @Override
    public Account findByIban(String iban) {
        return  accounts.stream()
                .filter(account->account.getIban().equals(iban))
                .findFirst()
                .orElse(null);
    }
}
