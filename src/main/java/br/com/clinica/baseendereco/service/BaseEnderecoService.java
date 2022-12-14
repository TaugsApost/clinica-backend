package br.com.clinica.baseendereco.service;

import java.util.List;

import br.com.clinica.baseendereco.entity.BaseEndereco;
import br.com.clinica.baseendereco.search.BaseEnderecoFilter;
import br.com.clinica.utils.IService;

public interface BaseEnderecoService extends IService<BaseEndereco, Long> {

	BaseEndereco consultarPorCep(String cep);

	List<String> consultarCidadePorEstado(String estado);

	List<String> consultarBairrosPorCidade(String cidade);

	List<String> consultarBairrosPorEstado(String estado);

	List<String> consultarRuasPorEstado(String estado);

	List<String> consultarRuasPorCidade(String cidade);

	List<String> consultarRuasPorBairro(String bairro);

	List<BaseEndereco> consultar(BaseEnderecoFilter filter);

}
