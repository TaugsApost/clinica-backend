package br.com.clinica.funcionario.search;

import java.util.ArrayList;
import java.util.List;

import br.com.clinica.funcionario.entity.Funcionario;

public class FuncionarioMapper {

	private static FuncionarioResponse toResponse(Funcionario from) {
		FuncionarioResponse to = new FuncionarioResponse();

		to.setId(from.getId());
		to.setDataContrato(from.getDataContrato());
		to.setNome(from.getNome());

		return to;
	}

	public static List<FuncionarioResponse> toResponse(List<Funcionario> from) {
		List<FuncionarioResponse> to = new ArrayList<FuncionarioResponse>();
		from.forEach(funcionario -> {
			to.add(toResponse(funcionario));
		});
		return to;
	}
}
