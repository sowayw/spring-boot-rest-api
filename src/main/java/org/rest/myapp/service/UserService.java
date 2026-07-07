package org.rest.myapp.service;

import java.util.List;
import java.util.UUID;

import org.rest.myapp.exception.UserNotFoundException;
import org.rest.myapp.mapper.UserMapper;
import org.rest.myapp.model.dto.UserChangePasswordDto;
import org.rest.myapp.model.dto.UserCreateDto;
import org.rest.myapp.model.dto.UserDto;
import org.rest.myapp.model.dto.UserUpdateDto;
import org.rest.myapp.model.entity.User;
import org.rest.myapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto getUserById(UUID uuid) {
        return userRepository.findById(uuid)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto createUser(UserCreateDto userCreateDto) {
        User newUser = new User();
        newUser.setPassword(userCreateDto.password());
        newUser.setUsername(userCreateDto.username());

        User savedUser = userRepository.save(newUser);

        return new UserDto(
                savedUser.getId(),
                savedUser.getUsername()
        );
    }

    public UserDto updateUser(UUID uuid, UserUpdateDto userUpdateDto) {
        return userRepository.findById(uuid).map(
                user -> {
                    user.setUsername(userUpdateDto.username());
                    userRepository.save(user);
                    return userMapper.toDto(user);
                }
        ).orElseThrow(() -> new UserNotFoundException("User not found"));

    }

    public void updateUserPassword(UUID uuid, UserChangePasswordDto newUser) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPassword(newUser.password());

        userRepository.save(user);
    }

    public void deleteUserById(UUID uuid) {
        userRepository.deleteById(uuid);
    }
}
