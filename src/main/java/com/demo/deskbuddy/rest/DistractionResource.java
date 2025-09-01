package com.demo.deskbuddy.rest;

import com.demo.deskbuddy.domain.DistractionHistory;
import com.demo.deskbuddy.dto.DistractionRequestDTO;
import com.demo.deskbuddy.service.DistractionHistoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DistractionResource {
    private final DistractionHistoryService distractionHistoryService;

    public DistractionResource(DistractionHistoryService distractionHistoryService) {
        this.distractionHistoryService = distractionHistoryService;
    }

    @PostMapping("/api/distractions/start")
    public DistractionHistory startDistraction(@RequestBody DistractionRequestDTO distractionRequestDTO) {
        return distractionHistoryService.startDistraction(distractionRequestDTO);
    }

    @PostMapping("/api/distractions/end")
    public DistractionHistory endDistraction(@RequestBody DistractionRequestDTO distractionRequestDTO) {
        return distractionHistoryService.endDistraction(distractionRequestDTO);
    }

}
