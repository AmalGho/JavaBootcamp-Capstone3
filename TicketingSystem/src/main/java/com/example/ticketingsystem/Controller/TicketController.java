package com.example.ticketingsystem.Controller;

import com.example.ticketingsystem.Api.ApiResponse;
import com.example.ticketingsystem.DTO.TicketDTO;
import com.example.ticketingsystem.Model.Ticket;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/getAllTickets")
    public ResponseEntity getAllTickets() {
        return ResponseEntity.status(200).body(ticketService.getAllTickets());
    }

    @GetMapping("/getManagerTickets")
    public ResponseEntity getManagerTickets(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(ticketService.getManagerTicket(user.getId()));
    }

    @PostMapping("/addTicket/{request_id}")
    public ResponseEntity addTicket(@AuthenticationPrincipal User user,@PathVariable Integer request_id, @RequestBody @Valid TicketDTO ticketDTO) {
        ticketService.addTicket(user.getId(),request_id, ticketDTO);
        return ResponseEntity.status(200).body(new ApiResponse("ticket added."));
    }

    @PutMapping("/updateTicket/{ticket_id}")
    public ResponseEntity updateTicket(@AuthenticationPrincipal User user, @PathVariable Integer ticket_id, @RequestBody @Valid Ticket ticket) {
        ticketService.updateTicket(user.getId(), ticket_id, ticket);
        return ResponseEntity.status(200).body(new ApiResponse("ticket updated"));
    }

    @DeleteMapping("/deleteTicket/{ticket_id}")
    public ResponseEntity deleteTicket(@PathVariable Integer ticket_id) {
        ticketService.deleteTicket(ticket_id);
        return ResponseEntity.status(200).body(new ApiResponse("ticket deleted."));
    }


}
