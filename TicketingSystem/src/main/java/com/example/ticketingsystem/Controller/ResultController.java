package com.example.ticketingsystem.Controller;

import com.example.ticketingsystem.Api.ApiResponse;
import com.example.ticketingsystem.DTO.ResultDTO;
import com.example.ticketingsystem.Model.Result;
import com.example.ticketingsystem.Model.User;
import com.example.ticketingsystem.Service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/result")
public class ResultController {

    private final ResultService resultService;

    @GetMapping("/getAllResults")
    public ResponseEntity getAllResults() {
        return ResponseEntity.status(200).body(resultService.getAllResults());
    }

    @PostMapping("/addResult/{request_id}")
    public ResponseEntity addResult(@AuthenticationPrincipal User user, @PathVariable Integer request_id, @RequestBody @Valid ResultDTO resultDTO) {
        resultService.addResult(user.getId(), request_id, resultDTO);
        return ResponseEntity.status(200).body(new ApiResponse("result added"));
    }

    @PutMapping("/updateResult/{request_id}")
    public ResponseEntity updateResult(@AuthenticationPrincipal User user, @PathVariable Integer request_id, @RequestBody @Valid Result result) {
        resultService.updateResult(user.getId(), request_id, result);
        return ResponseEntity.status(200).body(new ApiResponse("result updated"));
    }

    @DeleteMapping("/deleteResult/{result_id}")
    public ResponseEntity deleteResult(@PathVariable Integer result_id) {
        resultService.deleteResult(result_id);
        return ResponseEntity.status(200).body(new ApiResponse("result deleted"));
    }



}
