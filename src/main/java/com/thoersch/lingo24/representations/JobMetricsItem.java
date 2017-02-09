package com.thoersch.lingo24.representations;

public class JobMetricsItem {
    Integer WHITE_SPACES;
    Integer SEGMENTS;
    Integer WORDS;
    Integer CHARACTERS;

    public Integer getWHITE_SPACES() {
        return WHITE_SPACES;
    }

    public void setWHITE_SPACES(Integer WHITE_SPACES) {
        this.WHITE_SPACES = WHITE_SPACES;
    }

    public Integer getSEGMENTS() {
        return SEGMENTS;
    }

    public void setSEGMENTS(Integer SEGMENTS) {
        this.SEGMENTS = SEGMENTS;
    }

    public Integer getWORDS() {
        return WORDS;
    }

    public void setWORDS(Integer WORDS) {
        this.WORDS = WORDS;
    }

    public Integer getCHARACTERS() {
        return CHARACTERS;
    }

    public void setCHARACTERS(Integer CHARACTERS) {
        this.CHARACTERS = CHARACTERS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobMetricsItem)) return false;

        JobMetricsItem that = (JobMetricsItem) o;

        if (WHITE_SPACES != null ? !WHITE_SPACES.equals(that.WHITE_SPACES) : that.WHITE_SPACES != null) return false;
        if (SEGMENTS != null ? !SEGMENTS.equals(that.SEGMENTS) : that.SEGMENTS != null) return false;
        if (WORDS != null ? !WORDS.equals(that.WORDS) : that.WORDS != null) return false;
        return !(CHARACTERS != null ? !CHARACTERS.equals(that.CHARACTERS) : that.CHARACTERS != null);

    }

    @Override
    public int hashCode() {
        int result = WHITE_SPACES != null ? WHITE_SPACES.hashCode() : 0;
        result = 31 * result + (SEGMENTS != null ? SEGMENTS.hashCode() : 0);
        result = 31 * result + (WORDS != null ? WORDS.hashCode() : 0);
        result = 31 * result + (CHARACTERS != null ? CHARACTERS.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobMetricsItem{" +
                "WHITE_SPACES=" + WHITE_SPACES +
                ", SEGMENTS=" + SEGMENTS +
                ", WORDS=" + WORDS +
                ", CHARACTERS=" + CHARACTERS +
                '}';
    }
}
