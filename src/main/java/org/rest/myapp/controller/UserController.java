package org.rest.myapp.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.rest.myapp.model.dto.UserChangePasswordDto;
import org.rest.myapp.model.dto.UserCreateDto;
import org.rest.myapp.model.dto.UserDto;
import org.rest.myapp.model.dto.UserUpdateDto;
import org.rest.myapp.model.entity.User;
import org.rest.myapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{uuid}")
    public UserDto getUser(@PathVariable UUID uuid) {
        return userService.getUserById(uuid);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserCreateDto user) {
        return userService.createUser(user);
    };

    @PutMapping("/{uuid}")
    public UserDto updateUser(@PathVariable UUID uuid, @Valid @RequestBody UserUpdateDto user) {
        return userService.updateUser(uuid, user);
    }

    @PutMapping("/{uuid}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserPassword (@PathVariable UUID uuid, @Valid @RequestBody UserChangePasswordDto user) {
        userService.updateUserPassword(uuid, user);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID uuid) {
        userService.deleteUserById(uuid);
    }
}
