package com.demo.deskbuddy.service;

import com.demo.deskbuddy.domain.DistractionHistory;
import com.demo.deskbuddy.domain.SessionHistory;
import com.demo.deskbuddy.domain.Student;
import com.demo.deskbuddy.dto.DistractionHistoryDTO;
import com.demo.deskbuddy.repository.DistractionHistoryRepository;
import com.demo.deskbuddy.repository.SessionHistoryRepository;
import com.demo.deskbuddy.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class DistractionHistoryService {
    private final DistractionHistoryRepository distractionHistoryRepository;
    private final SessionHistoryRepository sessionHistoryRepository;
    private final StudentRepository studentRepository;

    public DistractionHistoryService(DistractionHistoryRepository distractionHistoryRepository, SessionHistoryRepository sessionHistoryRepository, StudentRepository studentRepository) {
        this.distractionHistoryRepository = distractionHistoryRepository;
        this.sessionHistoryRepository = sessionHistoryRepository;
        this.studentRepository = studentRepository;
    }

    public DistractionHistory createDistraction(DistractionHistoryDTO distractionHistoryDTO) {
        Optional<Student> optStudent = studentRepository.findByNik(distractionHistoryDTO.getNik());
        if (optStudent.isPresent()) {
            Optional<SessionHistory> optSessionHistory = sessionHistoryRepository.findByStudentIdAndSession(optStudent.get().getId(), distractionHistoryDTO.getSession());
            if (optSessionHistory.isPresent()) {
                SessionHistory sessionHistory = optSessionHistory.get();
                DistractionHistory distractionHistory = new DistractionHistory();
                distractionHistory.setSessionHistory(sessionHistory);
                distractionHistory.setDistractions(distractionHistoryDTO.getDistractions());
                distractionHistory.setDistractionStart(distractionHistoryDTO.getDistractionStart());
                distractionHistory.setDistractionEnd(distractionHistoryDTO.getDistractionEnd());
                distractionHistory.setTimeElapsed(getTimeElapsed(distractionHistoryDTO.getDistractionStart(), distractionHistoryDTO.getDistractionEnd()));
                distractionHistory = distractionHistoryRepository.save(distractionHistory);
                return distractionHistory;
            }
        }
        return null;
    }

    public Long getTimeElapsed(Instant start, Instant end){
        return ChronoUnit.SECONDS.between(start, end);
    }

}
