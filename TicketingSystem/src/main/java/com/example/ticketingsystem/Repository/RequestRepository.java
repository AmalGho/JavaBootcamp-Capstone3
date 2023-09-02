package com.example.ticketingsystem.Repository;

import com.example.ticketingsystem.Model.Manager;
import com.example.ticketingsystem.Model.Request;
import com.example.ticketingsystem.Model.SystemOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request findRequestById(Integer id);
    List<Request> findRequestBySystemOwner(SystemOwner systemOwner);

    Request findRequestByIdAndSystemOwner(Integer request_id, SystemOwner systemOwner);

    Request findRequestByIdAndManager(Integer request_id, Manager manager);

}
