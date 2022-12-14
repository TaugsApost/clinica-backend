package br.com.clinica.funcionario.search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import br.com.clinica.funcionario.entity.Funcionario;

public class FuncionarioMapper {

	SimpleDateFormat filterFormato = new SimpleDateFormat("dd/MM/yyyy");

	private static FuncionarioResponse toResponse(Funcionario from) {
		FuncionarioResponse to = new FuncionarioResponse();
		SimpleDateFormat filterFormato = new SimpleDateFormat("dd/MM/yyyy");
		filterFormato.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		to.setId(from.getId());
		to.setDataContrato(filterFormato.format(from.getDataContrato()));
		to.setSalario(String.format("R$%.2f", from.getSalario()));
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
