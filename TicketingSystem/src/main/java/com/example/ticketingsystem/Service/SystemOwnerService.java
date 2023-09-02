package com.example.ticketingsystem.Service;

import com.example.ticketingsystem.DTO.SystemOwnerDTO;
import com.example.ticketingsystem.Model.Manager;
import com.example.ticketingsystem.Model.SystemOwner;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Repository.AuthRepository;
import com.example.ticketingsystem.Repository.SystemOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemOwnerService {

    private final SystemOwnerRepository systemOwnerRepository;
    private final AuthRepository authRepository;
    public List<SystemOwner> getAllOwners() {
        return systemOwnerRepository.findAll();
    }

    public void addProfile(Integer owner_id, SystemOwnerDTO systemOwnerDTO) {
        User user = authRepository.findUserById(owner_id);
        SystemOwner systemOwner = new SystemOwner(null, systemOwnerDTO.getName(), systemOwnerDTO.getEmail(), user,null);
        systemOwnerRepository.save(systemOwner);
    }

    public void updateInfo(Integer owner_id, SystemOwner systemOwner) {
        SystemOwner oldOwner = systemOwnerRepository.findSystemOwnerById(owner_id);

        oldOwner.setName(systemOwner.getName());
        oldOwner.setEmail(systemOwner.getEmail());

        systemOwnerRepository.save(oldOwner);
    }

    public void deleteAccount(Integer owner_id) {
        User user = authRepository.findUserById(owner_id);
        authRepository.delete(user);
        systemOwnerRepository.delete(user.getSystemOwner());
    }
}
