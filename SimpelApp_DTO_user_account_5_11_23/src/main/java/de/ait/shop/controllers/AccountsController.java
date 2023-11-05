package de.ait.shop.controllers;

import de.ait.shop.dto.AccountRequestDTO;
import de.ait.shop.dto.AccountResponseDTO;
import de.ait.shop.dto.UserRequestDTO;
import de.ait.shop.dto.UserResponseDTO;
import de.ait.shop.servis.AccountService;
import de.ait.shop.servis.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
@Tag(name = "Accounts API", description = "Operations for managing accounts")
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountsService) {
        this.accountService = accountsService;
    }

    @PostMapping
    @Operation(description = "Create a new account")
    public AccountResponseDTO addAccount(@RequestBody AccountRequestDTO accountDTO) {
        AccountResponseDTO accountResponseDTO = accountService.addAccount(accountDTO);
        return accountResponseDTO;
    }


    @GetMapping
    @Operation(description = "Get all accounts")
    public List<AccountResponseDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}