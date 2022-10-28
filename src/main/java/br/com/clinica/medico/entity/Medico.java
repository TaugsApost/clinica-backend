package br.com.clinica.medico.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

	@ManyToOne
	@JoinColumn(name = "id_especialidade", nullable = false, foreignKey = @ForeignKey(name = "pk_especialidade"))
	private Especialidade especialidade;

	private Long crm;

	@JsonBackReference
	@OneToMany(mappedBy = "medico")
	List<Agenda> consultas;

}
