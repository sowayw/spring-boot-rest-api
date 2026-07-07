package org.rest.myapp.mapper;

import org.rest.myapp.model.dto.UserDto;
import org.rest.myapp.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername());
    }

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.uuid());
        user.setUsername(userDto.username());

        return user;
    }
}
