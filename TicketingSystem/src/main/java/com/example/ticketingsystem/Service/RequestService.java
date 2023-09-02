package com.example.ticketingsystem.Service;

import com.example.ticketingsystem.Api.ApiException;
import com.example.ticketingsystem.Model.Manager;
import com.example.ticketingsystem.Model.Request;
import com.example.ticketingsystem.Model.SystemOwner;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final SystemOwnerRepository systemOwnerRepository;
    private final AuthRepository authRepository;
    private final ManagerRepository managerRepository;
    private final TicketRepository ticketRepository;
    private final ResultRepository resultRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<Request> getOwnerRequest(Integer owner_id) {
        User user = authRepository.findUserById(owner_id);
        return requestRepository.findRequestBySystemOwner(user.getSystemOwner());
    }

    public void makeRequest(Integer owner_id, Request request) {
        User user = authRepository.findUserById(owner_id);
        request.setSystemOwner(user.getSystemOwner());
        request.setStatus("processing");
        requestRepository.save(request);
    }

    public void reRequest(Integer owner_id, Integer request_id, Request request) {
        User user = authRepository.findUserById(owner_id);
        Request oldRequest = requestRepository.findRequestByIdAndSystemOwner(request_id, user.getSystemOwner());

        if (oldRequest == null) throw new ApiException("this request not exist");



        if (oldRequest.getStatus().equalsIgnoreCase("ticket issued")){
            oldRequest.setRequest_type(request.getRequest_type());
            oldRequest.setBusiness_description(request.getBusiness_description());
            oldRequest.setIp(request.getIp());
            oldRequest.setUrl(request.getUrl());
            oldRequest.setStatus("reRequest processing");
            requestRepository.save(oldRequest);
        } else throw new ApiException("you cannot re request until ticket issued!");


    }


    public void deleteRequest(Integer request_id) {
        Request request = requestRepository.findRequestById(request_id);

        if (request == null) throw new ApiException("this request not exist");

        if (request.getStatus().equalsIgnoreCase("accepted") || request.getStatus().equalsIgnoreCase("reRequest accepted"))
            throw new ApiException("cannot delete accepted requests");

        if (request.getStatus().equalsIgnoreCase("closed")){
            requestRepository.delete(request);
            ticketRepository.delete(request.getTicket());
        }

        requestRepository.delete(request);
        ticketRepository.delete(request.getTicket());
        resultRepository.delete(request.getResult());
    }

    public void managerAcceptRequest(Integer manager_id, Integer request_id) {
        User user = authRepository.findUserById(manager_id);
        Request request = requestRepository.findRequestById(request_id);

        if (request == null) throw new ApiException("this request not exist");

        request.setManager(user.getManager());

        if (request.getStatus().equalsIgnoreCase("processing"))
            request.setStatus("accepted");
        if (request.getStatus().equalsIgnoreCase("reRequest processing"))
            request.setStatus("reRequest accepted");

        requestRepository.save(request);
    }

    public void assignOwnerToRequest(Integer owner_id, Integer request_id) {
        SystemOwner systemOwner = systemOwnerRepository.findSystemOwnerById(owner_id);
        Request request = requestRepository.findRequestById(request_id);

        if (systemOwner == null || request == null) throw new ApiException("cannot assign owner to request");

        request.setSystemOwner(systemOwner);
        requestRepository.save(request);
    }

    public void assignManagerToRequest(Integer manager_id, Integer request_id) {
        Manager manager = managerRepository.findManagerById(manager_id);
        Request request = requestRepository.findRequestById(request_id);

        if (manager == null || request == null) throw new ApiException("cannot assign manager to request");

        request.setManager(manager);
        requestRepository.save(request);
    }
}
