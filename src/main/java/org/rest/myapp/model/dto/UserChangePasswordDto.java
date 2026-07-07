package org.rest.myapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserChangePasswordDto(
    @NotBlank(message = "{password.not-blank}")
    @Size(min = 8, max = 255)
    String password
) {}
