package br.com.clinica.medico.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.clinica.especialidade.entity.Especialidade;
import br.com.clinica.especialidade.service.EspecialidadeService;
import br.com.clinica.exception.ServiceException;
import br.com.clinica.funcionario.service.FuncionarioService;
import br.com.clinica.medico.entity.Medico;
import br.com.clinica.pessoa.service.PessoaService;
import br.com.clinica.utils.AbstractServiceBean;

@Service
@Transactional
public class MedicoServiceBean extends AbstractServiceBean<Medico, Long> implements MedicoService {

	@Autowired
	EspecialidadeService especialidadeService;

	@Autowired
	PessoaService pessoaService;

	@Autowired
	FuncionarioService funcionarioService;

	private final PasswordEncoder encoder;

	public MedicoServiceBean(EntityManager em, PasswordEncoder encoder) {
		super(em);
		this.encoder = encoder;
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Medico> listarTodos() throws ServiceException {
		return listarTodosEntity();
	}

	@Override
	protected void beforeSave(Medico entity) throws ServiceException {
		Medico medico;
		try {
			medico = this.getEntityManager().createQuery(Medico.BUSCAR_POR_CRM, Medico.class)//
			        .setParameter("crm", entity.getCrm())//
			        .getSingleResult();
		} catch (NoResultException e) {
			medico = null;
		}
		if (medico != null) {
			throw new ServiceException("Número do CRM duplicado");
		}
		Especialidade especialidade = especialidadeService.pesquisarPorNome(entity.getEspecialidade().getNome());
		entity.setSenhaHash(encoder.encode(entity.getSenhaHash()));
		if (especialidade != null)
			entity.setEspecialidade(especialidade);
		super.beforeSave(entity);
	}

	@Override
	public Medico salvar(Medico entity) throws ServiceException {
		return this.salvarEntity(entity);
	}

	@Override
	public String excluir(Long id) throws ServiceException {
		return excluirEntity(id);
	}

	@Override
	public Medico detalhar(Long id) throws ServiceException {
		return this.detalharEntity(id);
	}

	@Override
	public List<Medico> listarPorEspecialidade(Especialidade especialidade) {
		return this.getEntityManager().createQuery(Medico.BUSCAR_POR_ESPECIALIDADE, Medico.class)//
		        .setParameter("especialidade", especialidade)//
		        .getResultList();
	}

}
