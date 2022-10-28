package br.com.clinica.pessoa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.clinica.exception.ServiceException;
import br.com.clinica.pessoa.entity.Pessoa;
import br.com.clinica.utils.AbstractServiceBean;

@Service
@Transactional
public class PessoaServiceBean extends AbstractServiceBean<Pessoa, Long> implements PessoaService {

	public PessoaServiceBean(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Pessoa> listarTodos() throws ServiceException {
		return listarTodosEntity();
	}

	@Override
	public Pessoa salvar(Pessoa entity) throws ServiceException {
		return salvarEntity(entity);
	}

	@Override
	public String excluir(Long id) throws ServiceException {
		return (this.excluirEntity(id));
	}

	@Override
	public Pessoa detalhar(Long id) throws ServiceException {
		return detalharEntity(id);
	}

}
