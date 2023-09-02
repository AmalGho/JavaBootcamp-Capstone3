package com.example.ticketingsystem.Repository;

import com.example.ticketingsystem.Model.SystemOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemOwnerRepository extends JpaRepository<SystemOwner, Integer> {
    SystemOwner findSystemOwnerById(Integer id);
}
