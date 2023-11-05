package de.ait.shop.servis;

import de.ait.shop.dto.UserRequestDTO;
import de.ait.shop.dto.UserResponseDTO;

import java.util.List;

public interface UsersService {
    UserResponseDTO addUser(UserRequestDTO userRequestDTO);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
}
