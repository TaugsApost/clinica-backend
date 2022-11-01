package br.com.clinica.especialidade.service;

import br.com.clinica.especialidade.entity.Especialidade;
import br.com.clinica.utils.IService;

public interface EspecialidadeService extends IService<Especialidade, Long> {

	Especialidade pesquisarPorNome(String nome);
}
