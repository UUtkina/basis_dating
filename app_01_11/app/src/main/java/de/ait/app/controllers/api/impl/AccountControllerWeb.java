package de.ait.app.controllers.api.impl;

import de.ait.app.model.Account;
import de.ait.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AccountControllerWeb {
    @Autowired
    private AccountService service;

    @PostMapping("/account")
    public String createUser(@RequestParam("account_iban") String iban, @RequestParam String balance){
        System.out.println("Start add user: " + iban + " "+ balance);
        service.createAccount(iban, Double.parseDouble(balance));
        //  return "addUserSuccess";
        return "redirect:/accounts";
    }
    @GetMapping("/accouts")
    public String listUsers(Model model){
        List<Account> allAccounts =service.getAllAccounts();
        model.addAttribute("accounts",allAccounts);
        return "all_accounts_view";
    }
}