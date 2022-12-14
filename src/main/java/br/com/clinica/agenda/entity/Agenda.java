package br.com.clinica.agenda.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.clinica.medico.entity.Medico;
import br.com.clinica.utils.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_agenda")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Agenda extends AbstractEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2734326106802416226L;

	public static final String PESQUISAR_HORARIOS_OCUPADOS = "SELECT agenda.horario FROM Agenda agenda WHERE "//
	        + "1 = 1 "//
	        + "AND agenda.codigoMedico = :codigoMedico "//
	        + "AND (agenda.data = :data)";

	public static final String PESQUISAR_AGENDAMENTO_POR_MEDICO = "SELECT agenda FROM Agenda agenda WHERE " //
	        + "agenda.codigoMedico = :codigoMedico "//
	        + "AND ((UPPER(REPLACE(agenda.nome, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :nome)) " //
	        + "AND (agenda.data = CAST(:data as Timestamp) or :data IS NULL) "//
	        + "order by agenda.data, agenda.horario";

	public static final String PESQUISAR_AGENDAMENTO = "SELECT agenda FROM Agenda agenda WHERE "//
	        + "1 = 1 " //
	        + "AND ((UPPER(REPLACE(agenda.nome, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :nome)) " //
	        + "AND (agenda.data :operador CAST(:data as Timestamp) or :data IS NULL) "//
	        + "order by ageenda.data, agenda.horario";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_agenda")
	private Long codigo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "id_medico", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "pk_agenda_medico"))
	private Medico medico;

	@Column(name = "id_medico")
	private Long codigoMedico;

	private Timestamp data;

	private String nome;

	private String email;

	private String telefone;

	private Integer horario;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return codigo;
	}

	@Override
	public void setId(Long id) {
		codigo = id;
	}
}
