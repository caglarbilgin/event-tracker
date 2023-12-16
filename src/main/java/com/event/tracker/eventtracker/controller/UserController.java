package com.event.tracker.eventtracker.controller;


import com.event.tracker.eventtracker.dto.AddressDTO;
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

    @PostMapping("/address")
    public AddressDTO createAddress(@RequestParam(name = "email") String email,
                                    @Valid @RequestBody AddressDTO address) {
        return userService.createAddress(email, address);
    }

    @GetMapping("address")
    public List<AddressDTO> getAddressByUser(@RequestParam(name = "email") String email) {
        return userService.getAddressByUser(email);
    }

    @DeleteMapping("/address")
    public AddressDTO deleteAddress(@RequestParam(name = "email") String email,
                                    @RequestParam(name = "address_id") String addressId) {
        return userService.deleteAddress(email, addressId);
    }

    @PatchMapping("/address")
    public AddressDTO updateAddress(@RequestParam(name = "email") String email,
                                    @RequestParam(name = "address_id") String addressId,
                                    @RequestBody AddressDTO addressDTO) {
        return userService.updateAddress(email, addressId, addressDTO);
    }

    @GetMapping("kafka")
    public void kafkaTest() {
        userService.kafkaTest();
    }
}
