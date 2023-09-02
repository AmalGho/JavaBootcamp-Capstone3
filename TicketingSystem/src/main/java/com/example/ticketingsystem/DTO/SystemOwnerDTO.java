package com.example.ticketingsystem.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class SystemOwnerDTO {
    @NotEmpty(message = "name should not be empty")
    @Length(min = 3, message = "username length should be more than 3 characters")
    private String name;

    @NotEmpty(message = "email should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "enter valid email")
    private String email;


   private Integer user_id;
}
