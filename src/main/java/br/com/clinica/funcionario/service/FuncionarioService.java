package br.com.clinica.funcionario.service;

import java.util.List;

import br.com.clinica.exception.ServiceException;
import br.com.clinica.funcionario.entity.Funcionario;
import br.com.clinica.funcionario.entity.Login;
import br.com.clinica.funcionario.search.FuncionarioFilter;
import br.com.clinica.funcionario.search.FuncionarioResponse;
import br.com.clinica.utils.IService;

public interface FuncionarioService extends IService<Funcionario, Long> {

	List<FuncionarioResponse> consultar(FuncionarioFilter filter) throws ServiceException;

	FuncionarioResponse logar(Login login);
}
