package com.thoersch.lingo24.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    private Long id;
    private String name;
    private Long domainId;
    private ProjectStatus projectStatus;
    private Long created;

    public Project() { }

    public Project(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.domainId = builder.domainId;
        this.projectStatus = builder.projectStatus;
        this.created = builder.created;
    }

    public static class Builder {
        private Long id;
        private String name;
        private Long domainId;
        private ProjectStatus projectStatus;
        private Long created;

        public Builder id(Long val) {
            this.id = val;
            return this;
        }

        public Builder name(String val) {
            this.name = val;
            return this;
        }

        public Builder domainId(Long val) {
            this.domainId = val;
            return this;
        }

        public Builder projectStatus(ProjectStatus val) {
            this.projectStatus = val;
            return this;
        }

        public Builder created(Long val) {
            this.created = val;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (domainId != null ? !domainId.equals(project.domainId) : project.domainId != null) return false;
        if (projectStatus != project.projectStatus) return false;
        return !(created != null ? !created.equals(project.created) : project.created != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        result = 31 * result + (projectStatus != null ? projectStatus.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", domainId=" + domainId +
                ", projectStatus=" + projectStatus +
                ", created=" + created +
                '}';
    }
}
