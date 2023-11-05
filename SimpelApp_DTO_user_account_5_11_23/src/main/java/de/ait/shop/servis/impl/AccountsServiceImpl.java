package de.ait.shop.servis.impl;

import de.ait.shop.dto.AccountRequestDTO;
import de.ait.shop.dto.AccountResponseDTO;
import de.ait.shop.models.Account;
import de.ait.shop.repositories.AccountsRepository;
import de.ait.shop.servis.AccountService;

import de.ait.shop.validation.IbanValidator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountsServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final IbanValidator ibanValidator;

    public AccountsServiceImpl(
            @Qualifier("accountsRepositoryList") AccountsRepository accountsRepository,
            @Qualifier("ibanValidatorRegex") IbanValidator ibanValidator) {
        this.accountsRepository = accountsRepository;
        this.ibanValidator = ibanValidator;
    }

    @Override
    public AccountResponseDTO addAccount(AccountRequestDTO accountRequestDTO) {
        String iban = accountRequestDTO.getIban();
        Double balance = accountRequestDTO.getBalance();

        // Validate IBAN
        if (!ibanValidator.isValidIban(iban)) {
            throw new IllegalArgumentException("Invalid IBAN format");
        }

        // Check if account already exists
        if (accountsRepository.findOneByIban(iban) != null) {
            throw new IllegalArgumentException("Account with IBAN " + iban + " already exists");
        }

        // Convert AccountRequestDTO to Account entity
        Account newAccount = AccountRequestDTO.toAccount(accountRequestDTO);

        // Save the new account entity
        Account savedAccount = accountsRepository.save(newAccount);

        // Convert saved Account entity to AccountResponseDTO
        return AccountResponseDTO.from(savedAccount);
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        List<Account> accounts = accountsRepository.findAll();
        return accounts.stream()
                .map(AccountResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO) {
        Account accountForUpdate = accountsRepository.findById(id);
        if (accountForUpdate == null) {
            throw new IllegalArgumentException("Account with id " + id + " not found");
        }
        String newIban = accountRequestDTO.getIban();
        Double newBalance = accountRequestDTO.getBalance();

        // Validate IBAN
        if (newIban != null && !newIban.isBlank() && !ibanValidator.isValidIban(newIban)) {
            throw new IllegalArgumentException("Invalid IBAN format");
        }

        // Update IBAN if it has changed and is valid
        if (newIban != null && !newIban.isBlank()) {
            accountForUpdate.setIban(newIban);
        }

        // Update balance if it's a valid value
        if (newBalance != null && newBalance >= 0) {
            accountForUpdate.setBalance(newBalance);
        }

        // Save the updated account entity
        Account updatedAccount = accountsRepository.save(accountForUpdate);
        return AccountResponseDTO.from(updatedAccount);
    }

}
