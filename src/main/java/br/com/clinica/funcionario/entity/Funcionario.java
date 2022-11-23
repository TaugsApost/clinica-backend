package br.com.clinica.funcionario.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.clinica.pessoa.entity.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_funcionario")
@PrimaryKeyJoinColumn(name = "id_funcionario", foreignKey = @ForeignKey(name = "pk_pessoa_funcionario"))
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Funcionario extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6564692844584619688L;

	private static final String CONSULTAR_QUERY_PARAMS = "1 = 1 "//
	        + "AND ((UPPER(REPLACE(funcionario.nome, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :nome)) " //
	        + "AND (funcionario.cidade = :cidade OR :cidade IS NULL) " //
	        + "AND (funcionario.estado = :estado OR :estado IS NULL) " //
	        + "AND (funcionario.bairro = :bairro OR :bairro IS NULL) " //
	        + "AND (funcionario.salario :operadorSalario :salario OR :salario IS NULL)"//
	        + "AND (funcionario.dataContrato :operadorData CAST(:dataContrato AS timestamp) OR :dataContrato IS NULL) " //
	        + "order by funcionario.nome";

	public static final String CONSULTAR_QUERY = "SELECT funcionario FROM Funcionario funcionario WHERE " + CONSULTAR_QUERY_PARAMS;

	public static final String VALIDAR_LOGIN = "SELECT funcionario FROM Funcionario funcionario "//
	        + "LEFT JOIN FETCH funcionario.contato contato "//
	        + "WHERE 1 = 1 "//
	        + "AND (UPPER(contato.email) = UPPER(:email)) "//
	        + "AND (funcionario.senhaHash = :senha)";

	public static final String PESQUISAR_POR_EMAIL = "SELECT funcionario FROM Funcionario funcionario "//
	        + "LEFT JOIN FETCH funcionario.contato contato "//
	        + "WHERE 1 = 1 "//
	        + "AND (UPPER(contato.email) = UPPER(:email))";

	private Timestamp dataContrato;

	private Double salario;

	private String senhaHash;

}
