package com.jiaqi.torino.datastore.exceptions.news;

public class DuplicateNewsException extends RuntimeException {

    public DuplicateNewsException() {
        super("Duplicate news found in database");
    }

    public DuplicateNewsException(String msg) {
        super(msg);
    }

    private static final long serialVersionUID = 1L;
    
}