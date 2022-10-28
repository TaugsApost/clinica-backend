package br.com.clinica.pessoa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.clinica.contato.entity.Contato;
import br.com.clinica.utils.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_pessoa")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pessoa extends AbstractEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -156512222525627899L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long codigo;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_contato", nullable = false, foreignKey = @ForeignKey(name = "pk_contato"))//
	// , insertable = false, updatable = false)
	private Contato contato;

	// @Column(name = "id_contato")
	// private Long codigoContato;

	private String nome;

	private String cep;

	private String logradouro;

	private Integer numeroCasa;

	private String bairro;

	private String cidade;

	private String estado;

	@Override
	public Long getId() {
		return codigo;
	}

	@Override
	public void setId(Long id) {
		codigo = id;
	}

}
