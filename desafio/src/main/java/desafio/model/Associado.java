package desafio.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
@Table(name="associado")
@Entity
public class Associado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="cpf",length=14)
	private String cpf;
	
	@Column(name="nome",nullable=false, length=80)
	private String nome;
	
	@OneToOne
	private Voto voto;
	
	public Associado() {
		
	}
}

