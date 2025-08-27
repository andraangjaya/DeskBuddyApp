package com.demo.deskbuddy.dto;


import com.demo.deskbuddy.domain.SessionHistory;
import jakarta.validation.constraints.NotNull;

public class SessionHistoryNikDTO {
    @NotNull
    private Long nik;

    @NotNull
    private Integer session;

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
}
