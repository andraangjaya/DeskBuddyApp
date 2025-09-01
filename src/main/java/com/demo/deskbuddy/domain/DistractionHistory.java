package com.demo.deskbuddy.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name = "distraction_history")
public class DistractionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequenceGenerator", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_history_id", nullable = false)
    private SessionHistory sessionHistory;

    @NotNull
    @Column(name = "distractions")
    private Integer distractions;

    @Column(name = "distraction_start")
    private Instant distractionStart;

    @Column(name = "distraction_end")
    private Instant distractionEnd;

    @Column(name = "time_elapsed")
    private Long timeElapsed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SessionHistory getSessionHistory() {
        return sessionHistory;
    }

    public void setSessionHistory(SessionHistory sessionHistory) {
        this.sessionHistory = sessionHistory;
    }

    public Integer getDistractions() {
        return distractions;
    }

    public void setDistractions(Integer distractions) {
        this.distractions = distractions;
    }

    public Instant getDistractionStart() {
        return distractionStart;
    }

    public void setDistractionStart(Instant distractionStart) {
        this.distractionStart = distractionStart;
    }

    public Instant getDistractionEnd() {
        return distractionEnd;
    }

    public void setDistractionEnd(Instant distractionEnd) {
        this.distractionEnd = distractionEnd;
    }

    public Long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(Long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
