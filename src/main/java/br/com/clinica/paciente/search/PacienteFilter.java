package br.com.clinica.paciente.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteFilter {

	private String nome;
	private String tipoSanguineo;
	private Double peso;
	private Double altura;
	private String cidade;
	private String estado;
	private String bairro;

}
