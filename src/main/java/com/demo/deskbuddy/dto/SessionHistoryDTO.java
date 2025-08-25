package com.demo.deskbuddy.dto;

import com.demo.deskbuddy.domain.Status;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class SessionHistoryDTO {
    @NotNull
    private Long nik;

    @NotNull
    private Integer session;

    @NotNull
    private Instant timeStarted;

    @NotNull
    private Instant timeFinished;

    private Integer totalDistraction;

    @NotNull
    private Status status;

    public Long getNik() {
        return nik;
    }

    public void setNik(Long nik) {
        this.nik = nik;
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
