package com.thoersch.lingo24.representations;

public class FileVM extends File {
    String content;

    public FileVM() { }

    public FileVM(File file) {
        super(file);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
