package de.ait.app.controllers.api.impl;

import de.ait.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import de.ait.app.model.User;

import java.util.List;

@RestController
public class RestUserController {

    @Autowired
    private UsersService service;

    @GetMapping("/api/users")
    public List<User>  getUsers(){
        return service.getAllUsers();
    }
}
