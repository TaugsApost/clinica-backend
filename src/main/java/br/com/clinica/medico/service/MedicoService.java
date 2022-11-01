package br.com.clinica.medico.service;

import java.util.List;

import br.com.clinica.especialidade.entity.Especialidade;
import br.com.clinica.medico.entity.Medico;
import br.com.clinica.utils.IService;

public interface MedicoService extends IService<Medico, Long> {

	List<Medico> listarPorEspecialidade(Especialidade especialidade);

}
