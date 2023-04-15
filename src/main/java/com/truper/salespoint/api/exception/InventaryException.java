package com.truper.salespoint.api.exception;

public class InventaryException extends RuntimeException {

	/**
	 * Class for manage don't found exception client
	 */
	private static final long serialVersionUID = 1L;

	public InventaryException(String productName,int inventaryQte) {
	    super(" Actualmente no se tiene suficiente producto: "+productName+" >>En existencia: "+inventaryQte);
	  }
}
