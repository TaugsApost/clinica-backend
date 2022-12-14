package br.com.clinica.agenda.search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import br.com.clinica.agenda.entity.Agenda;

public class AgendaMapper {

	private static AgendaResponse toResponse(Agenda from) {
		AgendaResponse to = new AgendaResponse();
		SimpleDateFormat filterFormato = new SimpleDateFormat("dd/MM/yyyy");
		filterFormato.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		to.setId(from.getId());
		to.setHorario(String.format("%02d:00", from.getHorario()));
		to.setData(filterFormato.format(from.getData()));
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
