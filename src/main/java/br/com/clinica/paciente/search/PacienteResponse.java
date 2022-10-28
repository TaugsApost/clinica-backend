package br.com.clinica.paciente.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteResponse {

	private Long id;
	private String nome;
	private String tipoSanguineo;
	private Double alutra;
	private Double peso;

}
