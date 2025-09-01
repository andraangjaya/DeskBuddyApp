package com.demo.deskbuddy.dto;

import java.time.Instant;

public class SessionHistoryViewDTO {
    private Integer session;
    private Instant timeStarted;
    private Instant timeFinished;
    private String status;
    private String firstName;
    private String lastName;
    private Long totalDistraction;

    public SessionHistoryViewDTO(Integer session, Instant timeStarted, Instant timeFinished, String status, String firstName, String lastName, Long totalDistraction) {
        this.session = session;
        this.timeStarted = timeStarted;
        this.timeFinished = timeFinished;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalDistraction = totalDistraction;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getTotalDistraction() {
        return totalDistraction;
    }

    public void setTotalDistraction(Long totalDistraction) {
        this.totalDistraction = totalDistraction;
    }
}
