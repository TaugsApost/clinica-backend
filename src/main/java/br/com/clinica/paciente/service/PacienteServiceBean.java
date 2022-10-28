package br.com.clinica.paciente.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.clinica.exception.ServiceException;
import br.com.clinica.paciente.entity.Paciente;
import br.com.clinica.paciente.search.PacienteFilter;
import br.com.clinica.paciente.search.PacienteMapper;
import br.com.clinica.paciente.search.PacienteResponse;
import br.com.clinica.utils.AbstractServiceBean;
import br.com.clinica.utils.Utils;

@Service
@Transactional
public class PacienteServiceBean extends AbstractServiceBean<Paciente, Long> implements PacienteService {

	public PacienteServiceBean(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Paciente> listarTodos() throws ServiceException {
		return this.listarTodosEntity();
	}

	@Override
	public Paciente salvar(Paciente entity) throws ServiceException {
		return this.salvarEntity(entity);
	}

	@Override
	public String excluir(Long id) throws ServiceException {
		return this.excluirEntity(id);
	}

	@Override
	public Paciente detalhar(Long id) throws ServiceException {
		return detalharEntity(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PacienteResponse> consultar(PacienteFilter filter) throws ServiceException {
		List<PacienteResponse> resultado = new ArrayList<PacienteResponse>();

		Query query = this.getEntityManager().createQuery(Paciente.CONUSLTAR_QUERY);
		query.setParameter("nome", Utils.stringLike(filter.getNome()));
		query.setParameter("tipoSanguineo", filter.getTipoSanguineo());
		query.setParameter("altura", filter.getAltura());
		query.setParameter("peso", filter.getPeso());
		query.setParameter("cidade", filter.getCidade());
		query.setParameter("estado", filter.getEstado());
		query.setParameter("bairro", filter.getBairro());

		resultado = PacienteMapper.toResponse(query.getResultList());

		return resultado;
	}

}
