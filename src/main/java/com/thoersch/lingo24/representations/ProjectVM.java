package com.thoersch.lingo24.representations;

import java.util.List;

public class ProjectVM extends Project {
    private List<JobVM> jobs;

    public ProjectVM() { }

    public ProjectVM(Project project) {
        super(project);
    }

    public List<JobVM> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobVM> jobs) {
        this.jobs = jobs;
    }
}
