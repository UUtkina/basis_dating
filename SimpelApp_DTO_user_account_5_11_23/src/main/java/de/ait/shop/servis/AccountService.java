package de.ait.shop.servis;

import de.ait.shop.dto.AccountRequestDTO;
import de.ait.shop.dto.AccountResponseDTO;
import de.ait.shop.dto.UserRequestDTO;
import de.ait.shop.dto.UserResponseDTO;

import java.util.List;

public interface AccountService {
    AccountResponseDTO addAccount(AccountRequestDTO accountRequestDTO);

    List<AccountResponseDTO> getAllAccounts();

    AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO);
}
