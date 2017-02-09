package com.thoersch.lingo24.representations;

import java.security.Timestamp;

public class Project {
    private Integer id;
    private String name;
    private Integer domainId;
    private ProjectStatus projectStatus;
    private Timestamp created;

    public Project() { }

    public Project(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.domainId = builder.domainId;
        this.projectStatus = builder.projectStatus;
        this.created = builder.created;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private Integer domainId;
        private ProjectStatus projectStatus;
        private Timestamp created;

        public Builder id(Integer val) {
            this.id = val;
            return this;
        }

        public Builder name(String val) {
            this.name = val;
            return this;
        }

        public Builder domainId(Integer val) {
            this.domainId = val;
            return this;
        }

        public Builder projectStatus(ProjectStatus val) {
            this.projectStatus = val;
            return this;
        }

        public Builder created(Timestamp val) {
            this.created = val;
            return this;
        }




        public Project build() {
            return new Project(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (id != project.id) return false;
        if (domainId != project.domainId) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (projectStatus != project.projectStatus) return false;
        return !(created != null ? !created.equals(project.created) : project.created != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + domainId;
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
