package br.com.clinica.pessoa.service;

import br.com.clinica.exception.ServiceException;
import br.com.clinica.pessoa.entity.Pessoa;
import br.com.clinica.utils.IService;

public interface PessoaService extends IService<Pessoa, Long> {

	Pessoa pesquisarPorNomeEndereco(String nome, String cep, Integer numero) throws ServiceException;
}
