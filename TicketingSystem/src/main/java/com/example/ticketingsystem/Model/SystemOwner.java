package com.example.ticketingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class SystemOwner {

    @Id
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Length(min = 3, message = "username length should be more than 3 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "email should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "enter valid email")
    @Column(columnDefinition = "varchar(50) unique")
    private String email;


    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "systemOwner")
    private List<Request> requests;
}
