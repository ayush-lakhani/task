package com.zidio.connect.auth;

import com.zidio.connect.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @Email String email,
        @NotBlank @Size(min = 8) String password,
        @NotBlank String fullName,
        UserRole role
) {}

