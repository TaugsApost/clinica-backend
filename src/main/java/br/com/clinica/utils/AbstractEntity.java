package br.com.clinica.utils;

import java.io.Serializable;

public abstract class AbstractEntity<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4358062660322731664L;

	protected static final int INITIAL_VALUE = 1;

	public static final String ID = "id";

	public abstract T getId();

	public abstract void setId(T id);
}
