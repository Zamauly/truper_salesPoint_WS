package com.truper.salespoint.api.exception;

public class ClienteNotFoundException extends RuntimeException {

	/**
	 * Class for manage don't found exception client
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(Long id) {
	    super("Could not find Cliente " + id);
	  }
}
