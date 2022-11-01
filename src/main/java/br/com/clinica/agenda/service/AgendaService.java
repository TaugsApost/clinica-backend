package br.com.clinica.agenda.service;

import java.util.List;

import br.com.clinica.agenda.entity.Agenda;
import br.com.clinica.agenda.search.AgendaFilter;
import br.com.clinica.agenda.search.AgendaResponse;
import br.com.clinica.exception.ServiceException;
import br.com.clinica.utils.IService;

public interface AgendaService extends IService<Agenda, Long> {

	List<Integer> listarHorariosOcupados(AgendaFilter filter);

	List<AgendaResponse> listarAgendaPorMedico(AgendaFilter filter);

	List<AgendaResponse> consultarAgenda(AgendaFilter filter) throws ServiceException;

}
