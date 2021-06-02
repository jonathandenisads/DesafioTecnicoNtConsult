package desafio.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.model.Pauta;
import desafio.repository.PautaRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;

	public Pauta salvarPauta(Pauta pauta) {
		return pautaRepository.save(pauta);
	}
	
	//Metodo para listar Todas as Pautas
		public List<Pauta> buscarPautas() {
			return pautaRepository.findAll();
		}
		
		public Pauta buscarPeloCodigo(int codigo) {
			return pautaRepository.findById(codigo);
		}

}
