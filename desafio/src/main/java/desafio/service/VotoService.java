package desafio.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.model.Associado;
import desafio.model.Pauta;
import desafio.model.Voto;
import desafio.repository.VotoRepository;

@Service
public class VotoService {
	
	@Autowired
	private VotoRepository votoRepository;

	
	
	public Voto salvarVoto(Voto voto) {
		return votoRepository.save(voto);
		
	}
	public List<Voto> buscarVotoPorAssociado(Associado associado) {
		
		return votoRepository.findByAssociado(associado);
		
	}
	
	public Voto buscarVotoPorPauta(Pauta pauta) {
		return votoRepository.findByPauta(pauta);
		
	}
	
	public List<Voto> buscarTodos(){
		return votoRepository.findAll();
		
	}

	
}
