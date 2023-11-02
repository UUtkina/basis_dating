package de.ait.app.services.impl;

import de.ait.app.model.Account;
import de.ait.app.repositories.AccountRepository;
import de.ait.app.services.AccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private static final String MSG_BLANK_ARGUMENT_ERR="The iban and balance should not be blank";
    private static final String MSG_EMAIL_NOT_CORRECT_ERR="Incorrect iban format";
    private static final String MSG_EMAIL_DUPLICATE_ERR="The user with iban is exist";


    private AccountRepository repository;


    public AccountServiceImpl(@Qualifier("getRepositoryAccount") AccountRepository repository) {
        this.repository = repository;
    }

    /*
    public UserServiceImpl(UserRepository getRepository) {
        this.repository = getRepository;
    }

     */

    @Override
    public Account createAccount(String Iban, Double balance) {
        try {
            checkUserArguments(Iban, balance);
            Account newAccount = new Account(Iban, balance);
            repository.save(newAccount);
            return newAccount;  // Return the newly created account
        } catch (Exception e) {
            String msg = String.format("Error create account: iban:%s balance:%s%n%s%n ", Iban, balance.toString(), e.getMessage());
            throw new RuntimeException(msg);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    private void checkUserArguments(String iban,Double balance){
        if (iban==null ||  balance==null || iban.isBlank() || balance<0 ) {
            throw new IllegalArgumentException(MSG_BLANK_ARGUMENT_ERR);
        }
      /*  if (email.indexOf('@')<0) {
            throw new IllegalArgumentException(MSG_EMAIL_NOT_CORRECT_ERR);
        }*/
        if(repository.findByIban(iban)!=null) {
            throw new IllegalArgumentException(MSG_EMAIL_DUPLICATE_ERR);
        }
    }
}
