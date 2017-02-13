package com.thoersch.lingo24.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {
    private Integer id;
    private JobStatus jobStatus;
    private long projectId;
    private Long serviceId;
    private String sourceLocale;
    private long sourceLocaleId;
    private String targetLocale;
    private long targetLocaleId;
    private long sourceFileId;
    private Long targetFileId;

    public Job() { }

    public Job(Builder builder) {
        this.id = builder.id;
        this.jobStatus = builder.jobStatus;
        this.projectId = builder.projectId;
        this.serviceId = builder.serviceId;
        this.sourceLocale = builder.sourceLocale;
        this.sourceLocaleId = builder.sourceLocaleId;
        this.targetLocale = builder.targetLocale;
        this.targetLocaleId = builder.targetLocaleId;
        this.sourceFileId = builder.sourceFileId;
        this.targetFileId = builder.targetFileId;
    }

    public static class Builder {
        private Integer id;
        private JobStatus jobStatus;
        private long projectId;
        private Long serviceId;
        private String sourceLocale;
        private long sourceLocaleId;
        private String targetLocale;
        private long targetLocaleId;
        private long sourceFileId;
        private Long targetFileId;

        public Builder id(Integer val) {
            this.id = val;
            return this;
        }

        public Builder jobStatus(JobStatus val) {
            this.jobStatus = val;
            return this;
        }

        public Builder projectId(Integer val) {
            this.projectId = val;
            return this;
        }

        public Builder serviceId(Long val) {
            this.serviceId = val;
            return this;
        }

        public Builder sourceLocale(String val) {
            this.sourceLocale = val;
            return this;
        }

        public Builder sourceLocaleId(Long val) {
            this.sourceLocaleId = val;
            return this;
        }

        public Builder targetLocale(String val) {
            this.targetLocale = val;
            return this;
        }

        public Builder targetLocaleId(Long val) {
            this.targetLocaleId = val;
            return this;
        }

        public Builder sourceFileId(Long val) {
            this.sourceFileId = val;
            return this;
        }

        public Builder targetFileId(Long val) {
            this.targetFileId = val;
            return this;
        }


        public Job build() {
            return new Job(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getSourceLocale() {
        return sourceLocale;
    }

    public void setSourceLocale(String sourceLocale) {
        this.sourceLocale = sourceLocale;
    }

    public long getSourceLocaleId() {
        return sourceLocaleId;
    }

    public void setSourceLocaleId(long sourceLocaleId) {
        this.sourceLocaleId = sourceLocaleId;
    }

    public String getTargetLocale() {
        return targetLocale;
    }

    public void setTargetLocale(String targetLocale) {
        this.targetLocale = targetLocale;
    }

    public long getTargetLocaleId() {
        return targetLocaleId;
    }

    public void setTargetLocaleId(long targetLocaleId) {
        this.targetLocaleId = targetLocaleId;
    }

    public long getSourceFileId() {
        return sourceFileId;
    }

    public void setSourceFileId(long sourceFileId) {
        this.sourceFileId = sourceFileId;
    }

    public long getTargetFileId() {
        return targetFileId;
    }

    public void setTargetFileId(Long targetFileId) {
        this.targetFileId = targetFileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

        if (projectId != job.projectId) return false;
        if (sourceLocaleId != job.sourceLocaleId) return false;
        if (targetLocaleId != job.targetLocaleId) return false;
        if (sourceFileId != job.sourceFileId) return false;
        if (id != null ? !id.equals(job.id) : job.id != null) return false;
        if (jobStatus != job.jobStatus) return false;
        if (serviceId != null ? !serviceId.equals(job.serviceId) : job.serviceId != null) return false;
        if (sourceLocale != null ? !sourceLocale.equals(job.sourceLocale) : job.sourceLocale != null) return false;
        if (targetLocale != null ? !targetLocale.equals(job.targetLocale) : job.targetLocale != null) return false;
        return !(targetFileId != null ? !targetFileId.equals(job.targetFileId) : job.targetFileId != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jobStatus != null ? jobStatus.hashCode() : 0);
        result = 31 * result + (int) (projectId ^ (projectId >>> 32));
        result = 31 * result + (serviceId != null ? serviceId.hashCode() : 0);
        result = 31 * result + (sourceLocale != null ? sourceLocale.hashCode() : 0);
        result = 31 * result + (int) (sourceLocaleId ^ (sourceLocaleId >>> 32));
        result = 31 * result + (targetLocale != null ? targetLocale.hashCode() : 0);
        result = 31 * result + (int) (targetLocaleId ^ (targetLocaleId >>> 32));
        result = 31 * result + (int) (sourceFileId ^ (sourceFileId >>> 32));
        result = 31 * result + (targetFileId != null ? targetFileId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobStatus=" + jobStatus +
                ", projectId=" + projectId +
                ", serviceId=" + serviceId +
                ", sourceLocale='" + sourceLocale + '\'' +
                ", sourceLocaleId=" + sourceLocaleId +
                ", targetLocale='" + targetLocale + '\'' +
                ", targetLocaleId=" + targetLocaleId +
                ", sourceFileId=" + sourceFileId +
                ", targetFileId=" + targetFileId +
                '}';
    }
}
