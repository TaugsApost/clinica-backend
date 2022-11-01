package br.com.clinica.agenda.search;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaFilter {

	private String nome;
	private Timestamp data;
	private Long codigoMedico;
	private String cep;
	private Integer numeroCasa;
}
