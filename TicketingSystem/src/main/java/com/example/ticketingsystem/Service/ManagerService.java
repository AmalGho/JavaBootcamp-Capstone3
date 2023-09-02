package com.example.ticketingsystem.Service;

import com.example.ticketingsystem.DTO.ManagerDTO;
import com.example.ticketingsystem.Model.Manager;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Repository.AuthRepository;
import com.example.ticketingsystem.Repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final AuthRepository authRepository;

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public void addProfile(Integer manager_id, ManagerDTO managerDTO) {
        User user = authRepository.findUserById(manager_id);
        Manager manager = new Manager(null, managerDTO.getName(), managerDTO.getEmail(), managerDTO.getEmployee_no(), managerDTO.getPosition(), user, null,null,null);
        managerRepository.save(manager);
    }

    public void updateInfo(Integer manager_id, Manager manager) {
        Manager oldManager = managerRepository.findManagerById(manager_id);

        oldManager.setName(manager.getName());
        oldManager.setEmail(manager.getEmail());
        oldManager.setEmployee_no(manager.getEmployee_no());
        oldManager.setPosition(manager.getPosition());

        managerRepository.save(oldManager);
    }

    public void deleteAccount(Integer manager_id) {
        User user = authRepository.findUserById(manager_id);
        authRepository.delete(user);
        managerRepository.delete(user.getManager());
    }

}
