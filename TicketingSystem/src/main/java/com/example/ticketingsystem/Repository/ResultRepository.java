package com.example.ticketingsystem.Repository;

import com.example.ticketingsystem.Model.Manager;
import com.example.ticketingsystem.Model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    Result findResultById(Integer id);

    Result findResultByIdAndManager(Integer result_id, Manager manager);
}
