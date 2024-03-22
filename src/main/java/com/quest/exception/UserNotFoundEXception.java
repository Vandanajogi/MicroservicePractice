package com.quest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundEXception extends RuntimeException {

	public UserNotFoundEXception(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
