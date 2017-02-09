package com.thoersch.lingo24.representations;

public class Job {
    private Integer id;
    private JobStatus jobStatus;
    private Integer projectId;
    private Integer serviceId;
    private String sourceLocale;
    private Integer sourceLocalId;
    private String targetLocale;
    private Integer targetLocaleId;
    private Integer sourceFileId;
    private Integer targetFileId;

    public Job() { }

    public Job(Builder builder) {
        this.id = builder.id;
        this.jobStatus = builder.jobStatus;
        this.projectId = builder.projectId;
        this.serviceId = builder.serviceId;
        this.sourceLocale = builder.sourceLocale;
        this.sourceLocalId = builder.sourceLocalId;
        this.targetLocale = builder.targetLocale;
        this.targetLocaleId = builder.targetLocaleId;
        this.sourceFileId = builder.sourceFileId;
        this.targetFileId = builder.targetFileId;
    }

    public static class Builder {
        private Integer id;
        private JobStatus jobStatus;
        private Integer projectId;
        private Integer serviceId;
        private String sourceLocale;
        private Integer sourceLocalId;
        private String targetLocale;
        private Integer targetLocaleId;
        private Integer sourceFileId;
        private Integer targetFileId;

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

        public Builder serviceId(Integer val) {
            this.serviceId = val;
            return this;
        }

        public Builder sourceLocale(String val) {
            this.sourceLocale = val;
            return this;
        }

        public Builder sourceLocalId(Integer val) {
            this.sourceLocalId = val;
            return this;
        }

        public Builder targetLocale(String val) {
            this.targetLocale = val;
            return this;
        }

        public Builder targetLocalId(Integer val) {
            this.targetLocaleId = val;
            return this;
        }

        public Builder sourceFileId(Integer val) {
            this.sourceFileId = val;
            return this;
        }

        public Builder targetFileId(Integer val) {
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getSourceLocale() {
        return sourceLocale;
    }

    public void setSourceLocale(String sourceLocale) {
        this.sourceLocale = sourceLocale;
    }

    public int getSourceLocalId() {
        return sourceLocalId;
    }

    public void setSourceLocalId(int sourceLocalId) {
        this.sourceLocalId = sourceLocalId;
    }

    public String getTargetLocale() {
        return targetLocale;
    }

    public void setTargetLocale(String targetLocale) {
        this.targetLocale = targetLocale;
    }

    public int getTargetLocaleId() {
        return targetLocaleId;
    }

    public void setTargetLocaleId(int targetLocaleId) {
        this.targetLocaleId = targetLocaleId;
    }

    public int getSourceFileId() {
        return sourceFileId;
    }

    public void setSourceFileId(int sourceFileId) {
        this.sourceFileId = sourceFileId;
    }

    public int getTargetFileId() {
        return targetFileId;
    }

    public void setTargetFileId(int targetFileId) {
        this.targetFileId = targetFileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

        if (id != null ? !id.equals(job.id) : job.id != null) return false;
        if (jobStatus != job.jobStatus) return false;
        if (projectId != null ? !projectId.equals(job.projectId) : job.projectId != null) return false;
        if (serviceId != null ? !serviceId.equals(job.serviceId) : job.serviceId != null) return false;
        if (sourceLocale != null ? !sourceLocale.equals(job.sourceLocale) : job.sourceLocale != null) return false;
        if (sourceLocalId != null ? !sourceLocalId.equals(job.sourceLocalId) : job.sourceLocalId != null) return false;
        if (targetLocale != null ? !targetLocale.equals(job.targetLocale) : job.targetLocale != null) return false;
        if (targetLocaleId != null ? !targetLocaleId.equals(job.targetLocaleId) : job.targetLocaleId != null)
            return false;
        if (sourceFileId != null ? !sourceFileId.equals(job.sourceFileId) : job.sourceFileId != null) return false;
        return !(targetFileId != null ? !targetFileId.equals(job.targetFileId) : job.targetFileId != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jobStatus != null ? jobStatus.hashCode() : 0);
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (serviceId != null ? serviceId.hashCode() : 0);
        result = 31 * result + (sourceLocale != null ? sourceLocale.hashCode() : 0);
        result = 31 * result + (sourceLocalId != null ? sourceLocalId.hashCode() : 0);
        result = 31 * result + (targetLocale != null ? targetLocale.hashCode() : 0);
        result = 31 * result + (targetLocaleId != null ? targetLocaleId.hashCode() : 0);
        result = 31 * result + (sourceFileId != null ? sourceFileId.hashCode() : 0);
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
                ", sourceLocalId=" + sourceLocalId +
                ", targetLocale='" + targetLocale + '\'' +
                ", targetLocaleId=" + targetLocaleId +
                ", sourceFileId=" + sourceFileId +
                ", targetFileId=" + targetFileId +
                '}';
    }
}
