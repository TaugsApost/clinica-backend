package br.com.clinica.contato.entity;

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
@Table(name = "tb_contato")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contato extends AbstractEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1964778825128134923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contato")
	private Long codigo;

	private String email;

	private String telefone;

	private String celular;

	@Override
	public Long getId() {
		return codigo;
	}

	@Override
	public void setId(Long id) {
		codigo = id;
	}
}
