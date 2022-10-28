package br.com.clinica.funcionario.search;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioResponse {

	private Long id;
	private String nome;
	private Timestamp dataContrato;

}
