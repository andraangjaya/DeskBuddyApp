package com.demo.deskbuddy.rest;

import com.demo.deskbuddy.domain.SessionHistory;
import com.demo.deskbuddy.dto.SessionHistoryDTO;
import com.demo.deskbuddy.dto.SessionHistoryNikDTO;
import com.demo.deskbuddy.dto.SessionHistoryViewDTO;
import com.demo.deskbuddy.repository.SessionHistoryRepository;
import com.demo.deskbuddy.service.SessionHistoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class SessionResource {

    private final SessionHistoryService sessionHistoryService;
    private final SessionHistoryRepository sessionHistoryRepository;

    public SessionResource(SessionHistoryService sessionHistoryService, SessionHistoryRepository sessionHistoryRepository) {
        this.sessionHistoryService = sessionHistoryService;
        this.sessionHistoryRepository = sessionHistoryRepository;
    }


    @PostMapping("/api/sessions")
    public SessionHistory createSessionHistory(@Valid @RequestBody SessionHistoryDTO sessionHistoryDTO) {
        return sessionHistoryService.createSession(sessionHistoryDTO);
    }

    @PostMapping("/api/session/start")
    public ResponseEntity<Void> startSession(@Valid @RequestBody SessionHistoryNikDTO sessionHistoryNikDTO) {
        sessionHistoryService.startSession(sessionHistoryNikDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/session/finish")
    public ResponseEntity<Void> finishSession(@Valid @RequestBody SessionHistoryNikDTO sessionHistoryNikDTO) {
        sessionHistoryService.finish(sessionHistoryNikDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/session/sessionhistory")
    public List<SessionHistoryViewDTO> viewSessionHistory(){
        return sessionHistoryRepository.findAllSessionHistory();
    }


}
