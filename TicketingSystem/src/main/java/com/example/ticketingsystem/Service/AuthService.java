package com.example.ticketingsystem.Service;

import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void adminRegister(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("ADMIN");
        authRepository.save(user);
    }

    public void managerRegister(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("MANAGER");
        authRepository.save(user);
    }


    public void systemOwnerRegister(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("OWNER");
        authRepository.save(user);
    }
}
