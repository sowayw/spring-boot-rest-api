package org.rest.myapp.service;

import java.util.List;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.rest.myapp.exception.UserNotFoundException;
import org.rest.myapp.mapper.UserMapper;
import org.rest.myapp.model.dto.UserChangePasswordDto;
import org.rest.myapp.model.dto.UserCreateDto;
import org.rest.myapp.model.dto.UserDto;
import org.rest.myapp.model.dto.UserUpdateDto;
import org.rest.myapp.model.entity.User;
import org.rest.myapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto createUser(UserCreateDto userCreateDto) {
        User newUser = userMapper.toEntity(userCreateDto);

        newUser.setPassword(passwordEncoder.encode(userCreateDto.password()));

        User savedUser = userRepository.save(newUser);

        return userMapper.toDto(savedUser);
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

    @Transactional
    public UserDto updateUser(UUID uuid, UserUpdateDto userUpdateDto) {
        return userRepository.findById(uuid).map(
                user -> {
                    user.setUsername(userUpdateDto.username());
                    return userMapper.toDto(user);
                }
        ).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Transactional
    public void updateUserPassword(UUID uuid, UserChangePasswordDto passwordDto) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode(passwordDto.password()));
    }

    public void deleteUserById(UUID uuid) {
        userRepository.deleteById(uuid);
    }
}
