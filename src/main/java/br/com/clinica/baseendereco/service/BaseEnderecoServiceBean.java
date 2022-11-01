package br.com.clinica.baseendereco.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.clinica.baseendereco.entity.BaseEndereco;
import br.com.clinica.exception.ServiceException;
import br.com.clinica.utils.AbstractServiceBean;

@Service
@Transactional
public class BaseEnderecoServiceBean extends AbstractServiceBean<BaseEndereco, Long> implements BaseEnderecoService {

	public BaseEnderecoServiceBean(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<BaseEndereco> listarTodos() throws ServiceException {
		return this.listarTodosEntity();
	}

	@Override
	public BaseEndereco salvar(BaseEndereco entity) throws ServiceException {
		return this.salvarEntity(entity);
	}

	@Override
	public String excluir(Long id) throws ServiceException {
		return this.excluirEntity(id);
	}

	@Override
	public BaseEndereco detalhar(Long id) throws ServiceException {
		return this.detalhar(id);
	}

	@Override
	public BaseEndereco consultarPorCep(String cep) {
		BaseEndereco baseEndereco = new BaseEndereco();
		try {
			baseEndereco = this.getEntityManager().createQuery(BaseEndereco.BUSCAR_ENDERECO_POR_CEP, BaseEndereco.class)//
			        .setParameter("cep", cep)//
			        .getSingleResult();
		} catch (NoResultException e) {
			baseEndereco = new BaseEndereco();
		}
		return baseEndereco;
	}

	@Override
	public List<String> consultarCidadePorEstado(String estado) {
		return this.getEntityManager().createQuery(BaseEndereco.BUSCAR_CIDADES_POR_ESTADO, String.class)//
		        .setParameter("estado", estado)//
		        .getResultList();
	}

	@Override
	public List<String> consultarBairrosPorCidade(String cidade) {
		return this.getEntityManager().createQuery(BaseEndereco.BUSCAR_BAIRROS_POR_CIDADE, String.class)//
		        .setParameter("cidade", cidade)//
		        .getResultList();
	}

	@Override
	public List<String> consultarBairrosPorEstado(String estado) {
		return this.getEntityManager().createQuery(BaseEndereco.BUSCAR_BAIRROS_POR_ESTADO, String.class)//
		        .setParameter("estado", estado)//
		        .getResultList();
	}

	@Override
	public List<String> consultarRuasPorEstado(String estado) {
		return this.getEntityManager().createQuery(BaseEndereco.BUSCAR_RUAS_POR_ESTADO, String.class)//
		        .setParameter("estado", estado)//
		        .getResultList();
	}

	@Override
	public List<String> consultarRuasPorCidade(String cidade) {
		return this.getEntityManager().createQuery(BaseEndereco.BUSCAR_RUAS_POR_CIDADE, String.class)//
		        .setParameter("cidade", cidade)//
		        .getResultList();
	}

	@Override
	public List<String> consultarRuasPorBairro(String bairro) {
		return this.getEntityManager().createQuery(BaseEndereco.BUSCAR_RUAS_POR_BAIRRO, String.class)//
		        .setParameter("bairro", bairro)//
		        .getResultList();
	}

}
