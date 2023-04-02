package com.truper.salespoint.api.exception;

public class ListaCompraNotFoundException extends RuntimeException {

	/**
	 * Class for manage don't found exception client
	 */
	private static final long serialVersionUID = 1L;

	public ListaCompraNotFoundException(Long id) {
	    super("Could not find Lista Compra " + id);
	  }
}
