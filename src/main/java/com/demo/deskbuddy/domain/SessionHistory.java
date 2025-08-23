package com.demo.deskbuddy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "session_history")
public class SessionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "session_id")
    private long sessionId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "session")
    private Integer session;

    @Column(name = "time_started")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime timeStarted;

    @Column(name = "time_finished")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime timeFinished;

    @Column(name = "total_distraction")
    private Integer totalDistraction;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public LocalDateTime getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(LocalDateTime timeStarted) {
        this.timeStarted = timeStarted;
    }

    public LocalDateTime getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(LocalDateTime timeFinished) {
        this.timeFinished = timeFinished;
    }

    public Integer getTotalDistraction() {
        return totalDistraction;
    }

    public void setTotalDistraction(Integer totalDistraction) {
        this.totalDistraction = totalDistraction;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
