package com.thoersch.lingo24.representations;

public class JobVM extends Job {
    private FileVM file;
    String language;
    String country;
    String languageCode;

    public JobVM(Job job) {
        super(job);
    }

    public FileVM getFile() {
        return file;
    }

    public void setFile(FileVM file) {
        this.file = file;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
