package com.truper.salespoint.api.exception;

import com.truper.salespoint.api.util.ExceptionsUtil;

public class NotFoundException extends RuntimeException {

	/**
	 * Class for manage don't found exception client
	 */
	private static final long serialVersionUID = 1L;

	public  NotFoundException(Object entityObject , Long id) {
	    super("Could not find "+ ExceptionsUtil.getCleanClassName(entityObject)+" : " + id);
	 }
}

