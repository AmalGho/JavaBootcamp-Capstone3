package com.example.ticketingsystem.Repository;

import com.example.ticketingsystem.Model.Manager;
import com.example.ticketingsystem.Model.SystemOwner;
import com.example.ticketingsystem.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findTicketById(Integer id);

    Ticket findTicketByIdAndManagerId(Integer ticket_id, Integer manager_id);

    List<Ticket> findTicketsByManager(Manager manager);

}
