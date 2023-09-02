package com.example.ticketingsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultDTO {
    @NotEmpty(message = "finding should not be empty")
    private String finding;

    private Integer request_id;
}
