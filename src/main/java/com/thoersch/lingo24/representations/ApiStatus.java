package com.thoersch.lingo24.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiStatus {
    private String version;
    private Long date;

    public ApiStatus() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiStatus apiStatus = (ApiStatus) o;

        if (version != null ? !version.equals(apiStatus.version) : apiStatus.version != null) return false;
        return !(date != null ? !date.equals(apiStatus.date) : apiStatus.date != null);

    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "APIStatus{" +
                "version='" + version + '\'' +
                ", date=" + date +
                '}';
    }
}
