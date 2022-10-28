package br.com.clinica.paciente.service;

import java.util.List;

import br.com.clinica.exception.ServiceException;
import br.com.clinica.paciente.entity.Paciente;
import br.com.clinica.paciente.search.PacienteFilter;
import br.com.clinica.paciente.search.PacienteResponse;
import br.com.clinica.utils.IService;

public interface PacienteService extends IService<Paciente, Long> {

	List<PacienteResponse> consultar(PacienteFilter filter) throws ServiceException;
}
