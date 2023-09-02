package com.example.ticketingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    private Integer id;

    @NotEmpty(message = "finding should not be empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String finding;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Request request;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Manager manager;
}
