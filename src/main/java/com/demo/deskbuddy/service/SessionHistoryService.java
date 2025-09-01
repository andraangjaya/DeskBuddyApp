package com.demo.deskbuddy.service;

import com.demo.deskbuddy.domain.SessionHistory;
import com.demo.deskbuddy.domain.Status;
import com.demo.deskbuddy.domain.Student;
import com.demo.deskbuddy.dto.SessionHistoryDTO;
import com.demo.deskbuddy.dto.SessionHistoryNikDTO;
import com.demo.deskbuddy.error.InvalidRequestException;
import com.demo.deskbuddy.repository.SessionHistoryRepository;
import com.demo.deskbuddy.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
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
            sessionHistory.setSession(sessionHistoryDTO.getSession());
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

    public void startSession(SessionHistoryNikDTO sessionHistoryNikDTO){
        Optional<Student> optStudent = studentRepository.findByNik(sessionHistoryNikDTO.getNik());
        if(optStudent.isPresent()){
            Instant today = Instant.now();
            ZonedDateTime zdt = today.atZone(ZoneId.of("UTC"));
            LocalDate date = LocalDate.of(zdt.getYear(), zdt.getMonth(), zdt.getDayOfMonth());

            Instant startOfDay = date.atStartOfDay().toInstant(ZoneOffset.UTC);

            Student student = optStudent.get();

            SessionHistory sessionHistory = new SessionHistory();
            sessionHistory.setStudent(student);

            Optional<SessionHistory> optSessionHistory = sessionHistoryRepository
                    .findByStudentIdAndTimeStartedIsNotNullAndTimeFinishedIsNull(student.getId());
            if (optSessionHistory.isEmpty()){
                sessionHistory.setSessionDate(startOfDay);
                sessionHistory.setSession(sessionHistoryNikDTO.getSession());
                sessionHistory.setTimeStarted(today);
                sessionHistory.setStatus(Status.IN_PROGRESS);
            } else {
                throw new InvalidRequestException("Previous Session is not Finished yet");
            }
            sessionHistory = sessionHistoryRepository.save(sessionHistory);
        }
    }

    public void finish(SessionHistoryNikDTO sessionHistoryNikDTO){
        Optional<Student> optStudent = studentRepository.findByNik(sessionHistoryNikDTO.getNik());
        if(optStudent.isPresent()){
            Student student = optStudent.get();
            Instant today = Instant.now();
            ZonedDateTime zdt = today.atZone(ZoneId.of("UTC"));
            LocalDate date = LocalDate.of(zdt.getYear(), zdt.getMonth(), zdt.getDayOfMonth());

            Instant startOfDay = date.atStartOfDay().toInstant(ZoneOffset.UTC);

            Instant endOfDay = date.atTime(23, 59, 59, 999_000_000).toInstant(ZoneOffset.UTC);
            Optional<SessionHistory> optSession = sessionHistoryRepository.findByStudentIdAndSessionAndSessionDateBetween(student.getId(), sessionHistoryNikDTO.getSession(), startOfDay, endOfDay);
            if(optSession.isPresent()){
                SessionHistory sessionHistory = optSession.get();
                sessionHistory.setTimeFinished(Instant.now());
                sessionHistory.setStatus(Status.DONE);
                sessionHistory = sessionHistoryRepository.save(sessionHistory);
            }
        }
    }

}
