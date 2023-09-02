package com.example.ticketingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {


    @Id
    private Integer id;

    @NotEmpty(message = "vulnerability name should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String vulnerability_name;

    @NotEmpty(message = "severity should not be empty")
    @Pattern(regexp = "\\W*((?i)critical|high|medium|low(?-i))\\W*", message = "severity should be critical | high | medium | low")
    @Column(columnDefinition = "varchar(10) not null")
    private String severity;

    @NotEmpty(message = "vulnerability description should not be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String vulnerability_description;

    @NotEmpty(message = "remediation should not be empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String remediation;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Manager manager;


    @OneToOne
    @MapsId
    @JsonIgnore
    private Request request;
}
