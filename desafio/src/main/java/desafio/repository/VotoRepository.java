package desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import desafio.model.Associado;
import desafio.model.Pauta;
import desafio.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Integer>{

	List<Voto> findByAssociado(Associado associado);
	Voto findByPauta(Pauta pauta);
	  
}
