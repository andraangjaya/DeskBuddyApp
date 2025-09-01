package com.demo.deskbuddy.service;

import com.demo.deskbuddy.domain.DistractionHistory;
import com.demo.deskbuddy.domain.SessionHistory;
import com.demo.deskbuddy.domain.Student;
import com.demo.deskbuddy.dto.DistractionRequestDTO;
import com.demo.deskbuddy.error.InvalidRequestException;
import com.demo.deskbuddy.repository.DistractionHistoryRepository;
import com.demo.deskbuddy.repository.SessionHistoryRepository;
import com.demo.deskbuddy.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
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

    public DistractionHistory startDistraction(DistractionRequestDTO distractionRequestDTO) {
        Optional<Student> optStudent = studentRepository.findByNik(distractionRequestDTO.getNik());
        if (optStudent.isPresent()) {
            Optional<SessionHistory> optSessionHistory = sessionHistoryRepository.findByStudentIdAndSession(optStudent.get().getId(), distractionRequestDTO.getSession());
            if (optSessionHistory.isPresent()) {
                SessionHistory sessionHistory = optSessionHistory.get();
                DistractionHistory distractionHistory = new DistractionHistory();
                distractionHistory.setSessionHistory(sessionHistory);

                List<DistractionHistory> list = distractionHistoryRepository.findAllBySessionHistoryId(sessionHistory.getId());
                if (list.isEmpty()){
                    distractionHistory.setDistractions(1);
                } else {
                    Optional<DistractionHistory> optDistractionHistory = distractionHistoryRepository
                            .findBySessionHistoryIdAndDistractionStartIsNotNullAndDistractionEndIsNull(sessionHistory.getId());
                    if(optDistractionHistory.isEmpty()){
                        distractionHistory.setDistractions(list.size() + 1);
                    } else {
                        throw new InvalidRequestException("Previous Distraction is not Finished yet");
                    }

                }


                distractionHistory.setDistractionStart(Instant.now());
                distractionHistory = distractionHistoryRepository.save(distractionHistory);
                return distractionHistory;
            }
        }
        return null;
    }

    public DistractionHistory endDistraction(DistractionRequestDTO distractionRequestDTO) {
        Optional<Student> optStudent = studentRepository.findByNik(distractionRequestDTO.getNik());
        if (optStudent.isPresent()) {
            Optional<SessionHistory> optionalSessionHistory = sessionHistoryRepository
                    .findByStudentIdAndSession(optStudent.get().getId(), distractionRequestDTO.getSession());
            if (optionalSessionHistory.isPresent()) {
                Optional<DistractionHistory> optionalDistractionHistory = distractionHistoryRepository
                        .findBySessionHistoryIdAndDistractionStartIsNotNullAndDistractionEndIsNull(optionalSessionHistory.get().getId());
                if (optionalDistractionHistory.isPresent()) {
                    DistractionHistory distractionHistory = optionalDistractionHistory.get();
                    distractionHistory.setDistractionEnd(Instant.now());
                    distractionHistory.setTimeElapsed(getTimeElapsed(distractionHistory.getDistractionStart(), distractionHistory.getDistractionEnd()));
                    distractionHistory = distractionHistoryRepository.save(distractionHistory);
                    return distractionHistory;
                }
            }
        }
        return null;
    }





    public Long getTimeElapsed(Instant start, Instant end){
        return ChronoUnit.SECONDS.between(start, end);
    }

}
