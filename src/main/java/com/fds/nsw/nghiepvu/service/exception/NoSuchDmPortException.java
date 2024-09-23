package com.fds.nsw.nghiepvu.service.exception;

import com.fds.nsw.kernel.orm.exception.NoSuchModelException;

/**
 * @author win_64
 */
public class NoSuchDmPortException extends NoSuchModelException {

	public NoSuchDmPortException() {
		super();
	}

	public NoSuchDmPortException(String msg) {
		super(msg);
	}

	public NoSuchDmPortException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchDmPortException(Throwable cause) {
		super(cause);
	}

}