package org.rest.myapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDto(
        @NotBlank(message = "{user.name.not-blank}")
        String username,

        @NotBlank(message = "{password.not-blank}")
        @Size(min = 8, max = 255)
        String password
) {}
