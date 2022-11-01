package br.com.clinica.agenda.search;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaResponse {

	private Long id;
	private String nome;
	private String nomeMedico;
	private Timestamp data;
	private Integer horario;
	private Boolean pacienteCadastrado;

}
