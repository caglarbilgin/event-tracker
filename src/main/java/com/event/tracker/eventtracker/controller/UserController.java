package com.event.tracker.eventtracker.controller;


import com.event.tracker.eventtracker.dto.UserDTO;
import com.event.tracker.eventtracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUser();
    }
}
