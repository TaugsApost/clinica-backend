package br.com.clinica.paciente.search;

import java.util.ArrayList;
import java.util.List;

import br.com.clinica.paciente.entity.Paciente;

public class PacienteMapper {

	public static PacienteResponse toResponse(Paciente from) {
		PacienteResponse to = new PacienteResponse();

		to.setId(from.getId());
		to.setAltura(String.format("%.2f m", from.getAltura()));
		to.setNome(from.getNome());
		to.setPeso(String.format("%.2f Kg", from.getPeso()));
		to.setTipoSanguineo(from.getTipoSanguineo());

		return to;
	}

	public static List<PacienteResponse> toResponse(List<Paciente> from) {
		List<PacienteResponse> to = new ArrayList<PacienteResponse>();
		from.forEach(paciente -> {
			to.add(toResponse(paciente));
		});
		return to;
	}
}
