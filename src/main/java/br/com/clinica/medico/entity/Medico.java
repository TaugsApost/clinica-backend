package br.com.clinica.medico.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.clinica.agenda.entity.Agenda;
import br.com.clinica.especialidade.entity.Especialidade;
import br.com.clinica.funcionario.entity.Funcionario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_medico")
@PrimaryKeyJoinColumn(name = "id_medico", foreignKey = @ForeignKey(name = "pk_funcionario_medico"))
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Medico extends Funcionario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6320399040147760013L;

	public static final String BUSCAR_POR_ID = "SELECT medico FROM Medico medico where medico.id = :id";

	public static final String BUSCAR_POR_ESPECIALIDADE = "SELECT medico From Medico medico WHERE medico.especialidade = :especialidade";

	public static final String BUSCAR_POR_CRM = "SELECT medico FROM Medico medico where medico.crm = :crm";

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_especialidade", nullable = false, foreignKey = @ForeignKey(name = "pk_especialidade"))
	private Especialidade especialidade;

	private Long crm;

	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	List<Agenda> consultas;

}
