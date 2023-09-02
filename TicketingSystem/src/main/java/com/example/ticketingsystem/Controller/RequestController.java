package com.example.ticketingsystem.Controller;

import com.example.ticketingsystem.Api.ApiResponse;
import com.example.ticketingsystem.Model.Request;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/getAllRequests")
    public ResponseEntity getAllRequests() {
        return ResponseEntity.status(200).body(requestService.getAllRequests());
    }

    @GetMapping("/getOwnerRequest")
    public ResponseEntity getOwnerRequest(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(requestService.getOwnerRequest(user.getId()));
    }

    @PostMapping("/makeRequest")
    public ResponseEntity makeRequest(@AuthenticationPrincipal User user, @RequestBody @Valid Request request){
        requestService.makeRequest(user.getId(), request);
        return ResponseEntity.status(200).body(new ApiResponse("request submitted successfully"));
    }

    @PutMapping("/reRequest/{request_id}")
    public ResponseEntity reRequest(@AuthenticationPrincipal User user, @PathVariable Integer request_id, @RequestBody @Valid Request request){
        requestService.reRequest(user.getId(), request_id, request);
        return ResponseEntity.status(200).body(new ApiResponse("reRequested success"));
    }

    @DeleteMapping("/deleteRequest/{request_id}")
    public ResponseEntity deleteRequest(@PathVariable Integer request_id) {
        requestService.deleteRequest(request_id);
        return ResponseEntity.status(200).body(new ApiResponse("request deleted"));
    }

    @PutMapping("/managerAcceptRequest/{request_id}")
    public ResponseEntity managerAcceptRequest(@AuthenticationPrincipal User user, @PathVariable Integer request_id) {
        requestService.managerAcceptRequest(user.getId(), request_id);
        return ResponseEntity.status(200).body(new ApiResponse("request accepted"));
    }
}
