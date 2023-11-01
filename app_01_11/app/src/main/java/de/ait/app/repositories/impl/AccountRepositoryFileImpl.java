package de.ait.app.repositories.impl;

import de.ait.app.model.Account;
import de.ait.app.model.User;
import de.ait.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component("AccountRepositoryFile")
@Qualifier("AccountRepositoryFile")
@Lazy
public class AccountRepositoryFileImpl implements AccountRepository {
    private final String fileName;
    private Long currentID = 0L;

    {
        System.out.println("File repository start");
    }

    public AccountRepositoryFileImpl(@Value("${accounts.file.name}") String fileName) {
        this.fileName = fileName;
        this.currentID = getLastID();
    }

    private long getLastID() {
        return findAll()
                .stream()
                .mapToLong(Account::getId)
                .max()
                .orElse(0);

    }

    @Override
    public void save(Account account) {
        System.out.println("save to file repo");
        account.setId(++currentID);
        String line = String.format("%d;%s;%s%n", account.getId(), account.getIban(), account.getBalance());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
        } catch (IOException e) {
            System.out.println("file save error");
        }
    }



    @Override
    public Account findByID(Long id) {
        return findAll().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    @Override
    public List<Account> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(this::parseLine)
                    .filter(Objects::nonNull)
                    .toList();

        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }

    }

    private Account parseLine(String line) {
        String[] tokenArray = line.split(";");
        try {
            return new Account(Long.parseLong(tokenArray[0]), tokenArray[1], Double.parseDouble(tokenArray[2]));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public void update(Account account) {

    }

      @Override
    public Account findByIban(String iban) {
        return findAll().stream()
                .filter(account -> account.getIban().equals(iban))
                .findFirst()
                .orElse(null);
    }
}


