package br.com.clinica.especialidade.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.clinica.especialidade.entity.Especialidade;
import br.com.clinica.exception.ServiceException;
import br.com.clinica.utils.AbstractServiceBean;
import br.com.clinica.utils.Utils;
@Service
@Transactional
public class EspecialidadeServiceBean extends AbstractServiceBean<Especialidade, Long> implements EspecialidadeService {

	public EspecialidadeServiceBean(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Especialidade> listarTodos() throws ServiceException {
		return this.listarTodosEntity();
	}

	@Override
	public Especialidade salvar(Especialidade entity) throws ServiceException {
		return this.salvarEntity(entity);
	}

	@Override
	public String excluir(Long id) throws ServiceException {
		return this.excluir(id);
	}

	@Override
	public Especialidade detalhar(Long id) throws ServiceException {
		return this.detalhar(id);
	}

	@Override
	public Especialidade pesquisarPorNome(String nome) {
		Especialidade especialidade;
		try {
			especialidade = this.getEntityManager().createQuery(Especialidade.PESQUISAR_POR_NOME, Especialidade.class)//
			        .setParameter("nome", Utils.stringLike(nome))//
			        .getSingleResult();
		} catch (NoResultException e) {
			especialidade = null;
		}
		return especialidade;
	}

}
