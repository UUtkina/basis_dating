package de.ait.shop.controllers;

import de.ait.shop.dto.UserRequestDTO;
import de.ait.shop.dto.UserResponseDTO;
import de.ait.shop.servis.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Users API", description = "Operations for managing users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    @Operation(description = "Create a new user")
    public UserResponseDTO addUser(@RequestBody UserRequestDTO userDTO) {
        UserResponseDTO userResponseDTO = usersService.addUser(userDTO);
        return userResponseDTO;
    }


    @GetMapping
    @Operation(description = "Get all users")
    public List<UserResponseDTO> getAllUsers() {
        return usersService.getAllUsers();
    }
}