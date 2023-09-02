package com.example.ticketingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    @Id
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Length(min = 3, message = "name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "email should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "enter valid email")
    @Column(columnDefinition = "varchar(50) unique")
    private String email;

    @NotEmpty(message = "employee number should not be empty")
    @Size(min = 4, max = 4, message = "employee_no length should be 4 characters")
    @Column(columnDefinition = "varchar(4) not null")
    private String employee_no;

    @NotEmpty(message = "position should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String position;


    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private List<Ticket> tickets;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private List<Request> requests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private List<Result> results;

}
