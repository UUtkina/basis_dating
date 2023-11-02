package de.ait.app.controllers.api.impl;

import de.ait.app.model.Account;
import de.ait.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public class RestAccountController {

    //private static final Logger logger = LoggerFactory.getLogger(RestAccountController.class);

    @Autowired
    private AccountService service;

    @PostMapping
  public Account createAccount(@RequestBody Account account){
        return service.createAccount(account.getIban(), account.getBalance());
    }

    @GetMapping
    public List<Account>getAccounts(){
        return service.getAllAccounts();
    }


}
