package com.thoersch.lingo24.representations;

public enum JobStatus {
    NEW("NEW"),
    ANALYZING("ANALYZING"),
    QUOTED("QUOTED"),
    IN_PROGRESS("IN_PROGRESS"),
    TRANSLATED("TRANSLATED"),
    CANCELLED("CANCELLED");

    JobStatus(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
