package de.ait.shop.dto;

import de.ait.shop.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder

public class AccountRequestDTO {
    private Long id;
    private String iban;
    private Double balance;

    public static Account toAccount(AccountRequestDTO accountRequestDTO){
        return  new Account(null, accountRequestDTO.getIban(), accountRequestDTO.getBalance());
    }

    public static List<Account> toAccount(List<AccountRequestDTO>accountRequestDTOS){
        return accountRequestDTOS.stream().map(AccountRequestDTO::toAccount)
                .toList();
    }
}
