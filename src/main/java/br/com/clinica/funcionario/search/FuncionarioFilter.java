package br.com.clinica.funcionario.search;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioFilter {

	private String nome;
	private String cidade;
	private String bairro;
	private String estado;
	private Double salario;
	private String operadorSalario;
	private String operadorData;
	private Timestamp dataContrato;

}
