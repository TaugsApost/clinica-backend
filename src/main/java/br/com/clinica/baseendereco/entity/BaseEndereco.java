package br.com.clinica.baseendereco.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.clinica.utils.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_endereco")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEndereco extends AbstractEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669982110511579049L;

	public static final String BUSCAR_ENDERECO_POR_CEP = "SELECT endereco FROM BaseEndereco endereco WHERE endereco.cep = :cep";
	public static final String BUSCAR_CIDADES_POR_ESTADO = "SELECT endereco.cidade FROM BaseEndereco endereco WHERE "//
	        + "endereco.estado = :estado order by endereco.cidade";
	public static final String BUSCAR_BAIRROS_POR_CIDADE = "SELECT endereco.bairro FROM BaseEndereco endereco WHERE " //
	        + "endereco.cidade = :cidade order by endereco.bairro";
	public static final String BUSCAR_BAIRROS_POR_ESTADO = "SELECT endereco.bairro FROM BaseEndereco endereco WHERE " //
	        + "endereco.estado = :estado order by endereco.bairro";
	public static final String BUSCAR_RUAS_POR_BAIRRO = "SELECT endereco.logradouro FROM BaseEndereco endereco WHERE " //
	        + "endereco.bairro = :bairro order by endereco.logradouro";
	public static final String BUSCAR_RUAS_POR_CIDADE = "SELECT endereco.logradouro FROM BaseEndereco endereco WHERE " //
	        + "endereco.cidade = :cidade order by endereco.logradouro";
	public static final String BUSCAR_RUAS_POR_ESTADO = "SELECT endereco.logradouro FROM BaseEndereco endereco WHERE " //
	        + "endereco.estado = :estado order by endereco.logradouro";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long id;

	private String cep;

	private String logradouro;

	private String bairro;

	private String cidade;

	private String estado;

}
