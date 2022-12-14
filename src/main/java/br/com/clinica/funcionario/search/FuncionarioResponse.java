package br.com.clinica.funcionario.search;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.clinica.funcionario.funcao.Funcao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioResponse {

	private Long id;
	private String nome;
	private String salario;
	private String dataContrato;
	// private Timestamp dataContrato;
	@Enumerated(EnumType.STRING)
	private Funcao funcao;

	public FuncionarioResponse(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

}
