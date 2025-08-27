package com.demo.deskbuddy.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "session_history")
public class SessionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequenceGenerator", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @NotNull
    @Column(name = "session", nullable = false)
    private Integer session;

    @NotNull
    @Column(name = "session_date",  nullable = false)
    private Instant sessionDate;

    @Column(name = "time_started", nullable = false)
    private Instant timeStarted;

    @Column(name = "time_finished")
    private Instant timeFinished;

    @Column(name = "total_distraction")
    private Integer totalDistraction;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public @NotNull Instant getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(@NotNull Instant sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Instant getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(Instant timeStarted) {
        this.timeStarted = timeStarted;
    }

    public Instant getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(Instant timeFinished) {
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
