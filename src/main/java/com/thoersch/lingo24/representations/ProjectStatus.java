package com.thoersch.lingo24.representations;

public enum ProjectStatus {
    CREATED("CREATED"),
    PENDING("PENDING"),
    QUOTED("QUOTED"),
    IN_PROGRESS("IN_PROGRESS"),
    FINISHED("FINISHED"),
    EXPIRED("EXPIRED"),
    CANCELLED("CANCELLED");

    ProjectStatus(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
