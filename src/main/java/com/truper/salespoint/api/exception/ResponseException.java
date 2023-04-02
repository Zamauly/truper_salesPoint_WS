package com.truper.salespoint.api.exception;

public class ResponseException {
	private String errorType;
	private String erorMsg;
	
	public ResponseException() {}
	
	public ResponseException(String errorType, String erorMsg) {
		super();
		this.errorType = errorType;
		this.erorMsg = erorMsg;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErorMsg() {
		return erorMsg;
	}

	public void setErorMsg(String erorMsg) {
		this.erorMsg = erorMsg;
	}
	
	
}
