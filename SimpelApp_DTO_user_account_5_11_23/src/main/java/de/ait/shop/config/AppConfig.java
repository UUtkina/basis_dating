package de.ait.shop.config;


import de.ait.shop.repositories.AccountsRepository;
import de.ait.shop.repositories.UsersRepository;
import de.ait.shop.repositories.impl.AccountsRepositoryListImpl;
import de.ait.shop.repositories.impl.UsersRepositaryFieImpl;
import de.ait.shop.repositories.impl.UsersRepositoryListImpl;
import de.ait.shop.servis.AccountService;
import de.ait.shop.servis.UsersService;
import de.ait.shop.servis.impl.AccountsServiceImpl;
import de.ait.shop.servis.impl.UsersServiceImpl;
import de.ait.shop.validation.EmailValidator;
import de.ait.shop.validation.IbanValidator;
import de.ait.shop.validation.PasswordValidator;
import de.ait.shop.validation.impl.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    public PasswordValidator passwordValidatorNotEmpty() {
        return new PasswordNotEmptyValidatorImpl();
    }

    @Bean
    public PasswordValidator passwordValidatorRegex() {
        return new PasswordValidatorRegexImpl();

    }

    @Bean
    public EmailValidator emailValidatorRegex() {
        return new EmailValidatorRegexImpl();
    }

    @Bean
    public EmailValidator emailValidatorNotEmpty() {
        return new EmaiNotEmptylValidatorImpl();
    }

    @Bean
    public UsersRepository usersRepositoryList() {
        return new UsersRepositoryListImpl();
    }

    @Bean
    public UsersRepository usersRepositoryFile() {
        return new UsersRepositaryFieImpl("users.txt");
    }

    @Bean
    public UsersService usersService(@Qualifier("usersRepositoryList") UsersRepository usersRepository,
                                     EmailValidator emailValidatorRegex,
                                     PasswordValidator passwordValidatorRegex) {
        return new UsersServiceImpl(usersRepository, emailValidatorRegex, passwordValidatorRegex);
    }

    @Bean
    public IbanValidator ibanValidatorRegex() {
        return new IbanValidatorRegexImpl();

    }

    @Bean
    public AccountsRepository accountsRepositoryList() {
        return new AccountsRepositoryListImpl();
    }


    @Bean
    public AccountService accountsService(@Qualifier("accountsRepositoryList") AccountsRepository accountsRepository,
                                          IbanValidator ibanValidatorRegex) {
        return new AccountsServiceImpl(accountsRepository, ibanValidatorRegex);
    }


}
