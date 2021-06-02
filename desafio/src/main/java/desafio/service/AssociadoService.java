package desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.model.Associado;
import desafio.repository.AssociadoRepository;

@Service
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	public Associado buscarAssociado(String cpf) {
		return associadoRepository.findByCpf(cpf);
		
	}
	
	

}
