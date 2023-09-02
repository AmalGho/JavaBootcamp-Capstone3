package com.example.ticketingsystem.Controller;

import com.example.ticketingsystem.Api.ApiResponse;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/adminRegister")
    public ResponseEntity adminRegister(@RequestBody @Valid User user) {
        authService.adminRegister(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("success registration for manager.."));
    }

    @PostMapping("/managerRegister")
    public ResponseEntity managerRegister(@RequestBody @Valid User user) {
        authService.managerRegister(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("success registration for manager.."));
    }

    @PostMapping("/systemOwnerRegister")
    public ResponseEntity systemOwnerRegister(@RequestBody @Valid User user) {
        authService.systemOwnerRegister(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("success registration for owner.."));
    }
}
