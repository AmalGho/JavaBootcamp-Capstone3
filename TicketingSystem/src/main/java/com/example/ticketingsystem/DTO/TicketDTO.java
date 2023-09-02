package com.example.ticketingsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketDTO {
    @NotEmpty(message = "vulnerability name should not be empty")
    private String vulnerability_name;

    @NotEmpty(message = "severity should not be empty")
    @Pattern(regexp = "\\W*((?i)critical|high|medium|low(?-i))\\W*", message = "severity should be critical | high | medium | low")
    private String severity;

    @NotEmpty(message = "vulnerability description should not be empty")
    private String vulnerability_description;

    @NotEmpty(message = "remediation should not be empty")
    private String remediation;

    private Integer request_id;
}
