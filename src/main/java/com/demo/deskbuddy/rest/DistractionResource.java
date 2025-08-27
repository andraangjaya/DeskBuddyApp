package com.demo.deskbuddy.rest;

import com.demo.deskbuddy.domain.DistractionHistory;
import com.demo.deskbuddy.dto.DistractionHistoryDTO;
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

    @PostMapping("/api/distractions")
    public DistractionHistory createDistractionHistory(@RequestBody DistractionHistoryDTO distractionHistoryDTO) {
        return distractionHistoryService.createDistraction(distractionHistoryDTO);
    }
}
