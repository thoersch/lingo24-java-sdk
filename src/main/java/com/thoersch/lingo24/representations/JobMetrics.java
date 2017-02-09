package com.thoersch.lingo24.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobMetrics {

    private JobMetricsItem TOTAL;
    private JobMetricsItem REPETITION_95_99;
    private JobMetricsItem REPETITION_85_94;
    private JobMetricsItem NO_MATCH;
    private JobMetricsItem FUZZY_MATCH_75_84;
    private JobMetricsItem NON_TRANSLATABL;
    private JobMetricsItem REPETITION_100;
    private JobMetricsItem REPETITION_ICE;
    private JobMetricsItem REPETITION_75_84;
    private JobMetricsItem LEVERAGED_MATCH;
    private JobMetricsItem FUZZY_MATCH_95_99;
    private JobMetricsItem FUZZY_MATCH_85_94;
    private JobMetricsItem IN_CONTEXT_EXACT_MATCH;

    public JobMetricsItem getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(JobMetricsItem TOTAL) {
        this.TOTAL = TOTAL;
    }

    public JobMetricsItem getREPETITION_95_99() {
        return REPETITION_95_99;
    }

    public void setREPETITION_95_99(JobMetricsItem REPETITION_95_99) {
        this.REPETITION_95_99 = REPETITION_95_99;
    }

    public JobMetricsItem getREPETITION_85_94() {
        return REPETITION_85_94;
    }

    public void setREPETITION_85_94(JobMetricsItem REPETITION_85_94) {
        this.REPETITION_85_94 = REPETITION_85_94;
    }

    public JobMetricsItem getNO_MATCH() {
        return NO_MATCH;
    }

    public void setNO_MATCH(JobMetricsItem NO_MATCH) {
        this.NO_MATCH = NO_MATCH;
    }

    public JobMetricsItem getFUZZY_MATCH_75_84() {
        return FUZZY_MATCH_75_84;
    }

    public void setFUZZY_MATCH_75_84(JobMetricsItem FUZZY_MATCH_75_84) {
        this.FUZZY_MATCH_75_84 = FUZZY_MATCH_75_84;
    }

    public JobMetricsItem getNON_TRANSLATABL() {
        return NON_TRANSLATABL;
    }

    public void setNON_TRANSLATABL(JobMetricsItem NON_TRANSLATABL) {
        this.NON_TRANSLATABL = NON_TRANSLATABL;
    }

    public JobMetricsItem getREPETITION_100() {
        return REPETITION_100;
    }

    public void setREPETITION_100(JobMetricsItem REPETITION_100) {
        this.REPETITION_100 = REPETITION_100;
    }

    public JobMetricsItem getREPETITION_ICE() {
        return REPETITION_ICE;
    }

    public void setREPETITION_ICE(JobMetricsItem REPETITION_ICE) {
        this.REPETITION_ICE = REPETITION_ICE;
    }

    public JobMetricsItem getREPETITION_75_84() {
        return REPETITION_75_84;
    }

    public void setREPETITION_75_84(JobMetricsItem REPETITION_75_84) {
        this.REPETITION_75_84 = REPETITION_75_84;
    }

    public JobMetricsItem getLEVERAGED_MATCH() {
        return LEVERAGED_MATCH;
    }

    public void setLEVERAGED_MATCH(JobMetricsItem LEVERAGED_MATCH) {
        this.LEVERAGED_MATCH = LEVERAGED_MATCH;
    }

    public JobMetricsItem getFUZZY_MATCH_95_99() {
        return FUZZY_MATCH_95_99;
    }

    public void setFUZZY_MATCH_95_99(JobMetricsItem FUZZY_MATCH_95_99) {
        this.FUZZY_MATCH_95_99 = FUZZY_MATCH_95_99;
    }

    public JobMetricsItem getFUZZY_MATCH_85_94() {
        return FUZZY_MATCH_85_94;
    }

    public void setFUZZY_MATCH_85_94(JobMetricsItem FUZZY_MATCH_85_94) {
        this.FUZZY_MATCH_85_94 = FUZZY_MATCH_85_94;
    }

    public JobMetricsItem getIN_CONTEXT_EXACT_MATCH() {
        return IN_CONTEXT_EXACT_MATCH;
    }

    public void setIN_CONTEXT_EXACT_MATCH(JobMetricsItem IN_CONTEXT_EXACT_MATCH) {
        this.IN_CONTEXT_EXACT_MATCH = IN_CONTEXT_EXACT_MATCH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobMetrics)) return false;

        JobMetrics that = (JobMetrics) o;

        if (TOTAL != null ? !TOTAL.equals(that.TOTAL) : that.TOTAL != null) return false;
        if (REPETITION_95_99 != null ? !REPETITION_95_99.equals(that.REPETITION_95_99) : that.REPETITION_95_99 != null)
            return false;
        if (REPETITION_85_94 != null ? !REPETITION_85_94.equals(that.REPETITION_85_94) : that.REPETITION_85_94 != null)
            return false;
        if (NO_MATCH != null ? !NO_MATCH.equals(that.NO_MATCH) : that.NO_MATCH != null) return false;
        if (FUZZY_MATCH_75_84 != null ? !FUZZY_MATCH_75_84.equals(that.FUZZY_MATCH_75_84) : that.FUZZY_MATCH_75_84 != null)
            return false;
        if (NON_TRANSLATABL != null ? !NON_TRANSLATABL.equals(that.NON_TRANSLATABL) : that.NON_TRANSLATABL != null)
            return false;
        if (REPETITION_100 != null ? !REPETITION_100.equals(that.REPETITION_100) : that.REPETITION_100 != null)
            return false;
        if (REPETITION_ICE != null ? !REPETITION_ICE.equals(that.REPETITION_ICE) : that.REPETITION_ICE != null)
            return false;
        if (REPETITION_75_84 != null ? !REPETITION_75_84.equals(that.REPETITION_75_84) : that.REPETITION_75_84 != null)
            return false;
        if (LEVERAGED_MATCH != null ? !LEVERAGED_MATCH.equals(that.LEVERAGED_MATCH) : that.LEVERAGED_MATCH != null)
            return false;
        if (FUZZY_MATCH_95_99 != null ? !FUZZY_MATCH_95_99.equals(that.FUZZY_MATCH_95_99) : that.FUZZY_MATCH_95_99 != null)
            return false;
        if (FUZZY_MATCH_85_94 != null ? !FUZZY_MATCH_85_94.equals(that.FUZZY_MATCH_85_94) : that.FUZZY_MATCH_85_94 != null)
            return false;
        return !(IN_CONTEXT_EXACT_MATCH != null ? !IN_CONTEXT_EXACT_MATCH.equals(that.IN_CONTEXT_EXACT_MATCH) : that.IN_CONTEXT_EXACT_MATCH != null);

    }

    @Override
    public int hashCode() {
        int result = TOTAL != null ? TOTAL.hashCode() : 0;
        result = 31 * result + (REPETITION_95_99 != null ? REPETITION_95_99.hashCode() : 0);
        result = 31 * result + (REPETITION_85_94 != null ? REPETITION_85_94.hashCode() : 0);
        result = 31 * result + (NO_MATCH != null ? NO_MATCH.hashCode() : 0);
        result = 31 * result + (FUZZY_MATCH_75_84 != null ? FUZZY_MATCH_75_84.hashCode() : 0);
        result = 31 * result + (NON_TRANSLATABL != null ? NON_TRANSLATABL.hashCode() : 0);
        result = 31 * result + (REPETITION_100 != null ? REPETITION_100.hashCode() : 0);
        result = 31 * result + (REPETITION_ICE != null ? REPETITION_ICE.hashCode() : 0);
        result = 31 * result + (REPETITION_75_84 != null ? REPETITION_75_84.hashCode() : 0);
        result = 31 * result + (LEVERAGED_MATCH != null ? LEVERAGED_MATCH.hashCode() : 0);
        result = 31 * result + (FUZZY_MATCH_95_99 != null ? FUZZY_MATCH_95_99.hashCode() : 0);
        result = 31 * result + (FUZZY_MATCH_85_94 != null ? FUZZY_MATCH_85_94.hashCode() : 0);
        result = 31 * result + (IN_CONTEXT_EXACT_MATCH != null ? IN_CONTEXT_EXACT_MATCH.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobMetrics{" +
                "TOTAL=" + TOTAL +
                ", REPETITION_95_99=" + REPETITION_95_99 +
                ", REPETITION_85_94=" + REPETITION_85_94 +
                ", NO_MATCH=" + NO_MATCH +
                ", FUZZY_MATCH_75_84=" + FUZZY_MATCH_75_84 +
                ", NON_TRANSLATABL=" + NON_TRANSLATABL +
                ", REPETITION_100=" + REPETITION_100 +
                ", REPETITION_ICE=" + REPETITION_ICE +
                ", REPETITION_75_84=" + REPETITION_75_84 +
                ", LEVERAGED_MATCH=" + LEVERAGED_MATCH +
                ", FUZZY_MATCH_95_99=" + FUZZY_MATCH_95_99 +
                ", FUZZY_MATCH_85_94=" + FUZZY_MATCH_85_94 +
                ", IN_CONTEXT_EXACT_MATCH=" + IN_CONTEXT_EXACT_MATCH +
                '}';
    }
}
