package com.demo.deskbuddy.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
