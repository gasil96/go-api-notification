package br.com.go.notification.exceptions;

import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1390879546248388037L;

	public BusinessException(String msg) {
		super(msg);
	}

}
