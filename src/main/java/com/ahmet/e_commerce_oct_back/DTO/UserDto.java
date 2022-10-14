package com.ahmet.e_commerce_oct_back.DTO;

import com.ahmet.e_commerce_oct_back.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotEmpty(message = "First name field should not be empty")
    private String firstName;

    @NotEmpty(message = "Last name field should not be empty")
    private String lastName;

    @Email(message = "Invalid email ID")
    @NotEmpty(message = "Email field should not be empty")
    private String email;

    @NotEmpty(message = "Password field should not be empty")
    @Size(min = 5, max = 1024, message = "Password must be min 5 chars")
    private String password;

    @NotEmpty(message = "Roles field should not be empty")
    private List<Role> roles;
}
