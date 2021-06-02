package desafio.model;
import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
@Table(name="pauta")
@Entity
public class Pauta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descricao")
	private String descricao;
	
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="assembleia_id")
	
	@Getter @Setter
	private Assembleia assembleia;
	
	@OneToMany(mappedBy="pauta")
	private List<Voto> votos;
	
	public Pauta() {
		
	}
}
