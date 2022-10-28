package br.com.clinica.paciente.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.clinica.pessoa.entity.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_paciente")
@PrimaryKeyJoinColumn(name = "id_paciente", foreignKey = @ForeignKey(name = "pk_pessoa_paciente"))
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paciente extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1366711197495045648L;

	public static final String CONSULTAR_QUERY_PARAMETROS = "1 = 1 " //
	        + "AND ((UPPER(REPLACE(paciente.nome, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :nome)) " //
	        + "AND (paciente.tipoSanguineo = :tipoSanguineo or :tipoSanguineo IS NULL) "//
	        + "AND (paciente.altura = :altura OR :altura IS NULL) " //
	        + "AND (paciente.peso = :peso OR :peso IS NULL) " //
	        + "AND (paciente.cidade = :cidade OR :cidade IS NULL) " //
	        + "AND (paciente.estado = :estado OR :estado IS NULL) " //
	        + "AND (paciente.bairro = :bairro OR :bairro IS NULL) " //
	        + "order by paciente.nome";

	public static final String CONUSLTAR_QUERY = "Select paciente From Paciente paciente Where " + CONSULTAR_QUERY_PARAMETROS;

	private Double peso;

	private Double altura;

	@Column(length = 3)
	private String tipoSanguineo;

}
