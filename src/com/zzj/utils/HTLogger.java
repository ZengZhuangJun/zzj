package com.zzj.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jum
 * @date 2011-5-25
 */
public class HTLogger {

	private Logger logger;

	public static HTLogger getLogger(String name) {
		return new HTLogger(LoggerFactory.getLogger(name));
	}

	public static HTLogger getLogger(Class clazz) {
		return new HTLogger(LoggerFactory.getLogger(clazz));
	}

	private HTLogger(Logger logger) {
		this.logger = logger;
	}

	public void debug(Object message) {
		logger.debug(getMessage(message));
	}

	public void debug(Object message, Throwable t) {
		logger.debug(getMessage(message), t);
	}

	public void info(Object message) {
		logger.info(getMessage(message));
	}

	public void info(Object message, Throwable t) {
		logger.info(getMessage(message), t);
	}

	public void warn(Object message) {
		logger.warn(getMessage(message));
	}

	public void warn(Object message, Throwable t) {
		logger.warn(getMessage(message), t);
	}

	public void error(Object message) {
		logger.error(getMessage(message));
	}

	public void error(Object message, Throwable t) {
		logger.error(getMessage(message), t);
	}

	String getMessage(Object message) {
		return String.valueOf(message);
	}

}
