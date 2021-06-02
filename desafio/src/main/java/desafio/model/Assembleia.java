package desafio.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString @EqualsAndHashCode

@Table(name="assembleia")
@Entity
public class Assembleia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome", nullable=false, length=45)
	private String nome;
	
	@Column(name="motivo", nullable=false, length=200)
	private String motivo;
	
	
	@OneToMany(mappedBy="assembleia")
	private List<Pauta> pautas;
	
	public Assembleia() {
		
	}
}
