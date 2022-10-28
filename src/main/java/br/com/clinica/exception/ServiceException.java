package br.com.clinica.exception;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 961951652423340472L;

	public ServiceException(String erro) {
		super(erro);
	}
}
