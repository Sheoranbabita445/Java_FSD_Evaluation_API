package com.springboot.libmanagement.dto.request;




import com.springboot.libmanagement.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(

        @NotBlank(message = "Username is required")
        @NotNull(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        @NotNull(message = "Password is required")
        String password,

        @NotNull(message = "Role is required")
        Role role

) {
}