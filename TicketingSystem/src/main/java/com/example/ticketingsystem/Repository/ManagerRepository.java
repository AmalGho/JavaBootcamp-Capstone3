package com.example.ticketingsystem.Repository;

import com.example.ticketingsystem.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository <Manager, Integer> {

    Manager findManagerById(Integer id);
}
