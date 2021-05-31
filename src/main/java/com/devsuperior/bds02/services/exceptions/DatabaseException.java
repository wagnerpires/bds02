package com.devsuperior.bds02.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DatabaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DatabaseException(String msg) {
        super(msg);
    }
	
}