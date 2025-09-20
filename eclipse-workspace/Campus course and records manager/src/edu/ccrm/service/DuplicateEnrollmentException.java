package edu.ccrm.service;

public class DuplicateEnrollmentException extends Exception {
  //a constructor that takes a message to describe the error 
	public DuplicateEnrollmentException(String message) {
		super(message);
	}
}
