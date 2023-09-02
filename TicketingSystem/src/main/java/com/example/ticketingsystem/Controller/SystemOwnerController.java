package com.example.ticketingsystem.Controller;

import com.example.ticketingsystem.Api.ApiResponse;
import com.example.ticketingsystem.DTO.ManagerDTO;
import com.example.ticketingsystem.DTO.SystemOwnerDTO;
import com.example.ticketingsystem.Model.Manager;
import com.example.ticketingsystem.Model.SystemOwner;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Service.SystemOwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owner")
public class SystemOwnerController {

    private final SystemOwnerService systemOwnerService;

    @GetMapping("/getAllOwners")
    public ResponseEntity getAllOwners() {
        return ResponseEntity.status(200).body(systemOwnerService.getAllOwners());
    }

    @PostMapping("/addProfile")
    public ResponseEntity addProfile(@AuthenticationPrincipal User user, @RequestBody @Valid SystemOwnerDTO systemOwnerDTO) {
        systemOwnerService.addProfile(user.getId(), systemOwnerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your profile completed."));
    }

    @PutMapping("/updateInfo")
    public ResponseEntity updateInfo(@AuthenticationPrincipal User user, @RequestBody @Valid SystemOwner systemOwner) {
        systemOwnerService.updateInfo(user.getSystemOwner().getId(), systemOwner);
        return ResponseEntity.status(200).body(new ApiResponse("your profile updated."));
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user) {
        systemOwnerService.deleteAccount(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("account deleted."));
    }
}
