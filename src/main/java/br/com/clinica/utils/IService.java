package br.com.clinica.utils;

import java.util.List;

import br.com.clinica.exception.ServiceException;

public interface IService<E extends AbstractEntity<T>, T extends Object> {

	List<E> listarTodos() throws ServiceException;

	E salvar(E entity) throws ServiceException;

	String excluir(T id) throws ServiceException;

	E detalhar(T id) throws ServiceException;
}
