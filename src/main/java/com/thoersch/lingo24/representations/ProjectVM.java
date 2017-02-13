package com.thoersch.lingo24.representations;

import java.util.ArrayList;
import java.util.List;

public class ProjectVM extends Project {
    private FileVM file;
    private List<Job> jobs = new ArrayList<Job>();

    public ProjectVM() { }

    public ProjectVM(Project project) {
        super(project);
    }

    public FileVM getFile() {
        return file;
    }

    public void setFile(FileVM file) {
        this.file = file;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void addJob(Job Job) {
        jobs.add(Job);
    }
}
