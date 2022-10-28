package br.com.clinica.medico.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.clinica.exception.ServiceException;
import br.com.clinica.medico.entity.Medico;
import br.com.clinica.utils.AbstractServiceBean;

@Service
@Transactional
public class MedicoServiceBean extends AbstractServiceBean<Medico, Long> implements MedicoService {

	public MedicoServiceBean(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Medico> listarTodos() throws ServiceException {
		return listarTodosEntity();
	}

	@Override
	public Medico salvar(Medico entity) throws ServiceException {
		return salvarEntity(entity);
	}

	@Override
	public String excluir(Long id) throws ServiceException {
		return excluirEntity(id);
	}

	@Override
	public Medico detalhar(Long id) throws ServiceException {
		return this.detalharEntity(id);
	}

}