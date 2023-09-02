package com.example.ticketingsystem.Controller;

import com.example.ticketingsystem.Api.ApiResponse;
import com.example.ticketingsystem.DTO.ManagerDTO;
import com.example.ticketingsystem.Model.Manager;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/manager")
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/getAllManagers")
    public ResponseEntity getAllManagers() {
        return ResponseEntity.status(200).body(managerService.getAllManagers());
    }

    @PostMapping("/addProfile")
    public ResponseEntity addProfile(@AuthenticationPrincipal User user, @RequestBody @Valid ManagerDTO managerDTO) {
        managerService.addProfile(user.getId(), managerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your profile completed."));
    }

    @PutMapping("/updateInfo")
    public ResponseEntity updateInfo(@AuthenticationPrincipal User user, @RequestBody @Valid Manager manager) {
        managerService.updateInfo(user.getManager().getId(), manager);
        return ResponseEntity.status(200).body(new ApiResponse("your profile updated."));
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user) {
        managerService.deleteAccount(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("account deleted."));
    }
}
