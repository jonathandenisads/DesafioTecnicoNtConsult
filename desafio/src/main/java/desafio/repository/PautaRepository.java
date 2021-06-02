package desafio.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import desafio.model.Pauta;



public interface PautaRepository extends JpaRepository<Pauta, Integer>{
	
	Pauta findById(int codigo);
}
