package com.thoersch.lingo24;

public class LingoException extends RuntimeException {
    public LingoException(Exception e) {
        super(e);
    }

    public LingoException(String s) {
        super(s);
    }


}
