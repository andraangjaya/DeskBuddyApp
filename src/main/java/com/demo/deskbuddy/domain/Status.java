package com.demo.deskbuddy.domain;

public enum Status {
    DONE("Done"),
    IN_PROGRESS("In Progress"),
    INCOMPLETE("Incomplete");

    private final String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
