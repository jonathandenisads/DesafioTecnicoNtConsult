package desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.model.Assembleia;
import desafio.repository.AssembleiaRepository;


@Service
public class AssembleiaService {
	
	@Autowired
	private AssembleiaRepository assembleiaRepository;
	
	
	//Metodo para listar Todas as Assembleias
	public List<Assembleia> buscarAssembleias() {
		return assembleiaRepository.findAll();
	}
	public Assembleia salvarAssembleia(Assembleia assembleia) {
		return assembleiaRepository.save(assembleia);
	}

}
