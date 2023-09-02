package com.example.ticketingsystem.Service;

import com.example.ticketingsystem.Api.ApiException;
import com.example.ticketingsystem.DTO.ResultDTO;
import com.example.ticketingsystem.Model.Manager;
import com.example.ticketingsystem.Model.Request;
import com.example.ticketingsystem.Model.Result;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final AuthRepository authRepository;
    private final RequestRepository requestRepository;
    private final ManagerRepository managerRepository;
    private final TicketRepository ticketRepository;


    public List<Result> getAllResults(){
        return resultRepository.findAll();
    }

    public void addResult(Integer manager_id, Integer request_id, ResultDTO resultDTO) {
        User user = authRepository.findUserById(manager_id);
        Request request = requestRepository.findRequestByIdAndManager(request_id, user.getManager());

        if (request == null) throw new ApiException("request not exist");

        Result result = new Result(null, resultDTO.getFinding(), request, user.getManager());
        request.setStatus("closed");

        resultRepository.save(result);
        requestRepository.save(request);
    }

    public void updateResult(Integer manager_id, Integer result_id, Result result) {
        User user = authRepository.findUserById(manager_id);
        Result oldResult = resultRepository.findResultByIdAndManager(result_id, user.getManager());

        if (oldResult == null) throw new ApiException("result of request not exist");

        oldResult.setFinding(result.getFinding());
        resultRepository.save(oldResult);
    }

    public void deleteResult(Integer result_id) {
        Result oldResult = resultRepository.findResultById(result_id);

        if (oldResult == null) throw new ApiException("result of request not exist");

        resultRepository.delete(oldResult);
        requestRepository.delete(oldResult.getRequest());
        ticketRepository.delete(oldResult.getRequest().getTicket());
    }


    public void assignManagerToResult(Integer result_id, Integer manager_id) {
        Manager manager = managerRepository.findManagerById(manager_id);
        Result result = resultRepository.findResultById(result_id);

        if (manager == null || result == null) throw new ApiException("cannot assign manager to result");

        result.setManager(manager);
        resultRepository.save(result);
    }

}
