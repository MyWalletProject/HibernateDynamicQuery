package com.example.exception;

public class InvalidSortingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidSortingException(String message){
		super(message);
	}
}
