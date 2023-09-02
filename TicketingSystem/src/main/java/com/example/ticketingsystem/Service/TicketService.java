package com.example.ticketingsystem.Service;

import com.example.ticketingsystem.Api.ApiException;
import com.example.ticketingsystem.DTO.TicketDTO;
import com.example.ticketingsystem.Model.Manager;
import com.example.ticketingsystem.Model.Request;
import com.example.ticketingsystem.Model.Ticket;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ManagerRepository managerRepository;
    private final AuthRepository authRepository;
    private final RequestRepository requestRepository;
    private final ResultRepository resultRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getManagerTicket(Integer manager_id) {
        User user = authRepository.findUserById(manager_id);
        return ticketRepository.findTicketsByManager(user.getManager());
    }


    public void addTicket(Integer manager_id, Integer request_id, TicketDTO ticketDTO) {
        User user = authRepository.findUserById(manager_id);
        Request request = requestRepository.findRequestByIdAndManager(request_id, user.getManager());

        if (request == null) throw new ApiException("request not exist");

        Ticket ticket = new Ticket(null, ticketDTO.getVulnerability_name(), ticketDTO.getSeverity(), ticketDTO.getVulnerability_description(), ticketDTO.getRemediation(), user.getManager(), request );

        ticket.getRequest().setStatus("Ticket issued");

        ticketRepository.save(ticket);
        requestRepository.save(ticket.getRequest());
    }

    public void updateTicket(Integer manager_id, Integer ticket_id, Ticket ticket) {
        User user = authRepository.findUserById(manager_id);
        Ticket oldTicket = ticketRepository.findTicketByIdAndManagerId(ticket_id, user.getManager().getId());

        if (oldTicket == null) throw new ApiException("this ticket not exist");

        oldTicket.setVulnerability_name(ticket.getVulnerability_name());
        oldTicket.setSeverity(ticket.getSeverity());
        oldTicket.setVulnerability_description(ticket.getVulnerability_description());
        oldTicket.setRemediation(ticket.getRemediation());

        oldTicket.getRequest().setStatus("closed");

        ticketRepository.save(oldTicket);
    }

    public void deleteTicket(Integer ticket_id) {
        Ticket oldTicket = ticketRepository.findTicketById(ticket_id);

        if (oldTicket == null) throw new ApiException("this ticket not exist");

        ticketRepository.delete(oldTicket);
        requestRepository.delete(oldTicket.getRequest());
        resultRepository.delete(oldTicket.getRequest().getResult());
    }



    public void assignManagerToTicket(Integer manager_id, Integer ticket_id) {
        Manager manager = managerRepository.findManagerById(manager_id);
        Ticket ticket = ticketRepository.findTicketById(ticket_id);

        if (manager == null || ticket == null) throw new ApiException("cannot assign manager to ticket");

        ticket.setManager(manager);
        ticketRepository.save(ticket);
    }


}
