package com.truper.salespoint.api.exception;

public class ProductoNotFoundException extends RuntimeException {

	/**
	 * Class for manage don't found exception client
	 */
	private static final long serialVersionUID = 1L;

	public ProductoNotFoundException(Long id) {
	    super("Could not find Producto " + id);
	  }
}
