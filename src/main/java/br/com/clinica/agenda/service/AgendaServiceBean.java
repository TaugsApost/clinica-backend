package br.com.clinica.agenda.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clinica.agenda.entity.Agenda;
import br.com.clinica.agenda.search.AgendaFilter;
import br.com.clinica.agenda.search.AgendaMapper;
import br.com.clinica.agenda.search.AgendaResponse;
import br.com.clinica.exception.ServiceException;
import br.com.clinica.paciente.service.PacienteService;
import br.com.clinica.pessoa.service.PessoaService;
import br.com.clinica.utils.AbstractServiceBean;
import br.com.clinica.utils.Utils;

@Service
@Transactional
public class AgendaServiceBean extends AbstractServiceBean<Agenda, Long> implements AgendaService {

	@Autowired
	PacienteService pacienteService;

	@Autowired
	PessoaService pessoaService;

	public AgendaServiceBean(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Agenda> listarTodos() throws ServiceException {
		return listarTodosEntity();
	}

	@Override
	public Agenda salvar(Agenda entity) throws ServiceException {
		return salvarEntity(entity);
	}

	@Override
	public String excluir(Long id) throws ServiceException {
		return excluirEntity(id);
	}

	@Override
	public Agenda detalhar(Long id) throws ServiceException {
		return detalharEntity(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> listarHorariosOcupados(AgendaFilter filter) {
		Query query = this.getEntityManager().createQuery(Agenda.PESQUISAR_HORARIOS_OCUPADOS, Integer.class);
		query.setParameter("codigoMedico", filter.getCodigoMedico());
		query.setParameter("data", filter.getData());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AgendaResponse> listarAgendaPorMedico(AgendaFilter filter) {
		Query query = this.getEntityManager().createQuery(Agenda.PESQUISAR_AGENDAMENTO_POR_MEDICO, Agenda.class);
		query.setParameter("codigoMedico", filter.getCodigoMedico());
		query.setParameter("data", filter.getData());
		query.setParameter("nome", Utils.stringLike(filter.getNome()));
		query.setParameter("horario", filter.getHorario());
		return AgendaMapper.toResponse(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AgendaResponse> consultarAgenda(AgendaFilter filter) throws ServiceException {
		Query query = this.getEntityManager().createQuery(Agenda.PESQUISAR_AGENDAMENTO, Agenda.class);
		query.setParameter("codigoMedico", filter.getCodigoMedico());
		query.setParameter("data", filter.getData());
		query.setParameter("nome", Utils.stringLike(filter.getNome()));
		query.setParameter("horario", filter.getHorario());
		List<AgendaResponse> resultado = AgendaMapper.toResponse(query.getResultList());
		// for (AgendaResponse response : resultado) {
		// Pessoa pessoa = pessoaService.pesquisarPorNomeEndereco(filter.getNome(), filter.getCep(), filter.getNumeroCasa());
		// if (pacienteService.detalhar(pessoa.getId()) == null)
		// response.setPacienteCadastrado(false);
		// else
		// response.setPacienteCadastrado(true);
		// }
		return resultado;
	}

}
