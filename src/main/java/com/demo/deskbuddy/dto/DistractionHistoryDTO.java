package com.demo.deskbuddy.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class DistractionHistoryDTO {
    @NotNull
    private Long nik;

    @NotNull
    private Integer session;

    @NotNull
    private Integer distractions;

    @NotNull
    private Instant distractionStart;

    @NotNull
    private Instant distractionEnd;

    public @NotNull Long getNik() {
        return nik;
    }

    public void setNik(@NotNull Long nik) {
        this.nik = nik;
    }

    public @NotNull Integer getSession() {
        return session;
    }

    public void setSession(@NotNull Integer session) {
        this.session = session;
    }

    public @NotNull Integer getDistractions() {
        return distractions;
    }

    public void setDistractions(@NotNull Integer distractions) {
        this.distractions = distractions;
    }

    public @NotNull Instant getDistractionStart() {
        return distractionStart;
    }

    public void setDistractionStart(@NotNull Instant distractionStart) {
        this.distractionStart = distractionStart;
    }

    public @NotNull Instant getDistractionEnd() {
        return distractionEnd;
    }

    public void setDistractionEnd(@NotNull Instant distractionEnd) {
        this.distractionEnd = distractionEnd;
    }
}
