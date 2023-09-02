package com.example.ticketingsystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class ManagerDTO {
    @NotEmpty(message = "name should not be empty")
    @Length(min = 3, message = "name length should be more than 3 characters")
    private String name;

    @NotEmpty(message = "email should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "enter valid email")
    private String email;

    @NotEmpty(message = "employee number should not be empty")
    @Size(min = 4, max = 4, message = "employee_no length should be 4 characters")
    private String employee_no;

    @NotEmpty(message = "position should not be empty")
    private String position;



    private Integer user_id;
}
