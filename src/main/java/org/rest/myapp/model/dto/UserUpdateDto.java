package org.rest.myapp.model.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateDto(
        @NotBlank
        String username
) {}
