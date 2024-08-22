package com.noah.photomanager.exceptions;

public class PhotoNotFoundException extends RuntimeException {
	public PhotoNotFoundException(String msg) {
		super(msg);
	}
	public PhotoNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
