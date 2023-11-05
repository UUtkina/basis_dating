package de.ait.shop.dto;

import de.ait.shop.models.Account;
import de.ait.shop.models.User;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponseDTO {
    private Long id;
    private String iban;
    private Double balance;

    public static AccountResponseDTO from(Account account){
        return new AccountResponseDTO(account.getId(),
                account.getIban(),
                account.getBalance());
    }

    public static List<AccountResponseDTO>from(List<Account>accounts){
        return accounts.stream()
                .map(AccountResponseDTO::from)
                .toList();
    }
}
