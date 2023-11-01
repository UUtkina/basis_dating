package de.ait.app;

import de.ait.app.repositories.AccountRepository;
import de.ait.app.model.User;
import de.ait.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    public Scanner getScanner(){
        return new Scanner(System.in);
    }



    @Bean
    public UserRepository getRepository(@Value("${users.repository.class}") String typeRepository){
        return context.getBean(typeRepository,UserRepository.class);
    }

    @Bean
    public AccountRepository getRepositoryAccount(@Value("${accounts.repository.class}") String typeRepository){
        return context.getBean(typeRepository, AccountRepository.class);


    }

}
