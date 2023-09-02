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
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "request type should not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String request_type;

    @NotEmpty(message = "business description should not be empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String business_description;

    @NotEmpty(message = "ip should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String ip;

    @NotEmpty(message = "URL should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String url;

    private String status;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
    @PrimaryKeyJoinColumn
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    @JsonIgnore
    private SystemOwner systemOwner;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Manager manager;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
    @PrimaryKeyJoinColumn
    private Result result;
}
