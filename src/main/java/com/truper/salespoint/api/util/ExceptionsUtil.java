package com.truper.salespoint.api.util;

public class ExceptionsUtil {

	public static String getCleanClassName(Object objectToEval) {
		String[] splitName = objectToEval.getClass().getName().split("\\.");
			
		return splitName[splitName.length-1];
	}
}
