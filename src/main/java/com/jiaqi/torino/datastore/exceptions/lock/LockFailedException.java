package com.jiaqi.torino.datastore.exceptions.lock;

public class LockFailedException extends RuntimeException {

    public LockFailedException(String string) {
        super(string);
	}

	/**
     *
     */
    private static final long serialVersionUID = 1L;
    
}