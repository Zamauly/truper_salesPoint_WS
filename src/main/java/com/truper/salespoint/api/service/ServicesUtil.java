package com.truper.salespoint.api.service;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicesUtil<T> {
	T objectToEval;
	private static final Logger _log = LoggerFactory.getLogger(ServicesUtil.class);

	public ServicesUtil(T objectToEval) {
		this.objectToEval = objectToEval;
	}

	public T getObjectToUpdate(T objectToUpdate) {
		for (Field field : objectToUpdate.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String name = field.getName();
			Object valueToChange = null;
			Object valueUpdate = null;
			try {
				valueToChange = field.get(objectToEval);

				if (valueToChange == null) {
					valueUpdate = field.get(objectToUpdate);
					field.set(objectToEval, valueUpdate);
					_log.info(" Updated  Field: " + name + " Value: " + valueUpdate + " Type: "
							+ valueUpdate.getClass().getName());
				} else {

					_log.info(" Declared Field: " + name + " Value: " + valueToChange + " Type: "
							+ valueToChange.getClass().getName());
				}

				valueUpdate = field.get(objectToEval);
				_log.info(" Last Mod Field: " + name + " Value: " + valueUpdate + " Type: "
						+ valueUpdate.getClass().getName());
			} catch (IllegalArgumentException | IllegalAccessException err) {
				// TODO Auto-generated catch block
				_log.error(" Error at trying to build: " + objectToEval.getClass().getName() + " to update. Error: "
						+ err.getMessage());
			}
		}
		return objectToEval;
	}
}
