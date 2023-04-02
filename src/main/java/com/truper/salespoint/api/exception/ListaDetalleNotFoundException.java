package com.truper.salespoint.api.exception;

public class ListaDetalleNotFoundException extends RuntimeException {

	/**
	 * Class for manage don't found exception lista detalle
	 */
	private static final long serialVersionUID = 1L;

	public ListaDetalleNotFoundException(Long id) {
	    super("Could not find Lista Detalle " + id);
	  }
}
