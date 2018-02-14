package com.model;

public class ErrorClazz  {
	private int code;
	private String message;
	/*generate getters and setters of code and message*/
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/*generate constructor using fields*/
	public ErrorClazz(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
	
	
}
