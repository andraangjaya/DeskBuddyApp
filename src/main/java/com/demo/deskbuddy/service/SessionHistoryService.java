package com.demo.deskbuddy.service;

import com.demo.deskbuddy.domain.SessionHistory;
import com.demo.deskbuddy.domain.Student;
import com.demo.deskbuddy.dto.SessionHistoryDTO;
import com.demo.deskbuddy.repository.SessionHistoryRepository;
import com.demo.deskbuddy.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionHistoryService {
    private final SessionHistoryRepository sessionHistoryRepository;
    private final StudentRepository studentRepository;

    public SessionHistoryService(SessionHistoryRepository sessionHistoryRepository, StudentRepository studentRepository) {
        this.sessionHistoryRepository = sessionHistoryRepository;
        this.studentRepository = studentRepository;
    }



    public SessionHistory createSession(SessionHistoryDTO sessionHistoryDTO){
        Optional<Student> optStudent = studentRepository.findByNik(sessionHistoryDTO.getNik());
        if(optStudent.isPresent()){
            Student student = optStudent.get();
            SessionHistory sessionHistory = new SessionHistory();
            sessionHistory.setStudent(student);
            sessionHistory.setTimeStarted(sessionHistoryDTO.getTimeStarted());
            sessionHistory.setTimeFinished(sessionHistoryDTO.getTimeFinished());
            sessionHistory.setTotalDistraction(sessionHistoryDTO.getTotalDistraction());
            sessionHistory.setStatus(sessionHistoryDTO.getStatus());
            sessionHistory = sessionHistoryRepository.save(sessionHistory);
            return sessionHistory;
        } else {
            return null;
        }
    }
}
