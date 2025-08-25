package com.demo.deskbuddy.rest;

import com.demo.deskbuddy.domain.SessionHistory;
import com.demo.deskbuddy.dto.SessionHistoryDTO;
import com.demo.deskbuddy.service.SessionHistoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class SessionResource {

    private final SessionHistoryService sessionHistoryService;

    public SessionResource(SessionHistoryService sessionHistoryService) {
        this.sessionHistoryService = sessionHistoryService;
    }


    @PostMapping("/api/sessions")
    public SessionHistory createSessionHistory(@Valid @RequestBody SessionHistoryDTO sessionHistoryDTO) {
        return sessionHistoryService.createSession(sessionHistoryDTO);
    }


}
