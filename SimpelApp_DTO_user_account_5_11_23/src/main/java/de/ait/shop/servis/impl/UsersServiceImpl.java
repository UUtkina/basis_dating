package de.ait.shop.servis.impl;

import de.ait.shop.dto.UserRequestDTO;
import de.ait.shop.dto.UserResponseDTO;
import de.ait.shop.models.User;
import de.ait.shop.repositories.UsersRepository;
import de.ait.shop.servis.UsersService;
import de.ait.shop.validation.EmailValidator;
import de.ait.shop.validation.PasswordValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;

    // Constructor with qualifiers to specify which beans to inject
    public UsersServiceImpl(
            @Qualifier("usersRepositoryList") UsersRepository usersRepository,
            @Qualifier("emailValidatorRegex") EmailValidator emailValidator,
            @Qualifier("passwordValidatorRegex") PasswordValidator passwordValidator) {
        this.usersRepository = usersRepository;
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        String email = userRequestDTO.getEmail();
        String password = userRequestDTO.getPassword();

        // Validate email and password
        emailValidator.validate(email);
        passwordValidator.validate(password);

        // Check if user already exists
        User existedUser = usersRepository.findOneByEmail(email);
        if (existedUser != null) {
            throw new IllegalArgumentException("Email is already in use");
        }

        // Convert UserRequestDTO to User entity
        User newUser = UserRequestDTO.toUser(userRequestDTO);

        // Save the new user entity
        User savedUser = usersRepository.save(newUser);

        // Convert saved User entity to UserResponseDTO
        return UserResponseDTO.from(savedUser);
    }


    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = usersRepository.findAll();
        return users.stream()
                .map(UserResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User userForUpdate = usersRepository.findById(id);

        if (userForUpdate == null) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }

        String newEmail = userRequestDTO.getEmail();
        String newPassword = userRequestDTO.getPassword();

        if (newEmail != null && !newEmail.isBlank()) {
            emailValidator.validate(newEmail);
            userForUpdate.setEmail(newEmail);
        }

        if (newPassword != null && !newPassword.isBlank()) {
            passwordValidator.validate(newPassword);
            userForUpdate.setPassword(newPassword);
        }

        User updatedUser = usersRepository.save(userForUpdate);
        return UserResponseDTO.from(updatedUser);
    }
}
