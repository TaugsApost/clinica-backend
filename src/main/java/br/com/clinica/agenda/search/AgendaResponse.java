package br.com.clinica.agenda.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaResponse {

	private Long id;
	private String nome;
	private String nomeMedico;
	private String data;
	private String horario;
	private Boolean pacienteCadastrado;

}
