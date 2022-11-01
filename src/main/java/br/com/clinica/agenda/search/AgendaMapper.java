package br.com.clinica.agenda.search;

import java.util.ArrayList;
import java.util.List;

import br.com.clinica.agenda.entity.Agenda;

public class AgendaMapper {

	private static AgendaResponse toResponse(Agenda from) {
		AgendaResponse to = new AgendaResponse();

		to.setId(from.getId());
		to.setHorario(from.getHorario());
		to.setData(from.getData());
		to.setNome(from.getNome());
		to.setNomeMedico(from.getMedico().getNome());

		return to;
	}

	public static List<AgendaResponse> toResponse(List<Agenda> from) {
		List<AgendaResponse> to = new ArrayList<AgendaResponse>();
		from.forEach(agenda -> {
			to.add(toResponse(agenda));
		});
		return to;
	}

}
