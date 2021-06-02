package desafio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
@Table(name="voto")
@Entity
public class Voto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="status", length=5)
	public String status;// true = Sim       false= Não
	
	
	@ManyToOne
	@JoinColumn(name = "pauta_id")
	public Pauta pauta;
	
	
	@OneToOne
	@JoinColumn(name="associado_id")
	public Associado associado;
	
	public Voto() {}
	
	
	
}
