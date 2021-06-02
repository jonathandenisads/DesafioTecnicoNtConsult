package desafio.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import desafio.model.Associado;
import desafio.model.Pauta;
import desafio.model.ResponseCpf;
import desafio.model.Voto;
import desafio.service.AssociadoService;
import desafio.service.PautaService;
import desafio.service.VotoService;

@Controller
public class AssociadoResources {

	@Autowired
	private AssociadoService associadoService;

	@Autowired
	private PautaService pautaService;

	@Autowired
	private VotoService votoService;

	public static Associado assoc;

	@PostMapping("/associado")

	public String pegarcpf(Associado associado) {
		assoc = associado;
		Associado AssociadoA = associadoService.buscarAssociado(assoc.getCpf());// busco o associado pelo cpf

		if (AssociadoA == null) {// GARANTE QUE O CPF ESTEJA PREVIAMENTE CADASTRADO NA BASE DE DADOS PARA EXIBIR
									// A NOVA TELA
			return "redirect:/";
		}
		return "redirect:/api/" + associado.getCpf();
	}

	// COMUNICA COM A API DE CPF E DEIXA O ASSOCIADO VOTAR SOMENTE SE O STATUS DOR
	// ABLE_TO VOTE
	// SE O STATUS QUE A API RETORNAR FOR UNABLE_TO_VOTE O SISTEMA REDIRECIONA PARA
	// A TELA INICIAL
	@GetMapping("/api/{cpf}")
	@ResponseBody
	public String verificarcpf(@PathVariable String cpf, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		RestTemplate template = new RestTemplate();

		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https").host("user-info.herokuapp.com")
				.path("users/" + cpf).build();
		ResponseEntity<ResponseCpf> entity = template.getForEntity(uri.toUriString(), ResponseCpf.class);

		if (entity.getBody().getStatus().equals("ABLE_TO_VOTE")) {
			resp.sendRedirect("/principalassociado");
		} else if (entity.getBody().getStatus().equals("UNABLE_TO_VOTE")) {
			resp.sendRedirect("/unablevote");
		}

		return cpf;

	}

	@GetMapping("/principalassociado")
	public ModelAndView listarPautas() {
		ModelAndView mv = new ModelAndView("telaprincipalassociado");
		List<Pauta> pautas = pautaService.buscarPautas();

		Associado AssociadoA = associadoService.buscarAssociado(assoc.getCpf());// pegar o associado através de seu cpf
		List<Voto> votos = votoService.buscarVotoPorAssociado(AssociadoA);// pegar voto do associado através do cpf que
																			// logou no sistema

		List<Pauta> pautaParaRemover = new ArrayList<>();
		for (Pauta pauta : pautas) {// ADICIONA PAUTAS NA LISTA PARA SEREM REMOVIDAS, PARA QUE O ASSOCIADO NÃO VOTE
									// DUAS VEZES NA MESMA PAUTA
			for (Voto voto : votos) {
				if (pauta.getId() == voto.getPauta().getId()) {
					pautaParaRemover.add(pauta);
				}

			}
		}
		pautas.removeAll(pautaParaRemover);// REMOVE A PAUTA

		mv.addObject("pautas", pautas);// APRESENTA LISTA DE PAUTAS CONSOLIDADAS
		return mv;
	}

	@GetMapping("/{codigo}")
	public ModelAndView redirecionarParaVotacao(@PathVariable("codigo") Integer codigo) {
		// ADICIONA A PAUTA SELECIONADA EM OUTRA TELA E ENVIA O OBJETO VOTO PARA SER
		// SALVO
		Pauta pauta = pautaService.buscarPeloCodigo(codigo);
		ModelAndView mv = new ModelAndView("telavotacao");
		mv.addObject("pautas", pauta);
		mv.addObject("votacao", new Voto());
		return mv;

	}

	@PostMapping("/{codigo}")
	public String salvarVoto(@PathVariable("codigo") Integer codigo, Voto voto,
			RedirectAttributes redirectAttributes, HttpServletResponse response,
			HttpSession httpSession) {
	
			
		httpSession.setMaxInactiveInterval(5);
		
		long votosSim = 0;
		long votosNao = 0;

		Pauta pauta = pautaService.buscarPeloCodigo(codigo);
		Associado AssociadoA = associadoService.buscarAssociado(assoc.getCpf());

		voto.setAssociado(AssociadoA);
		voto.setPauta(pauta);
		votoService.salvarVoto(voto);

		List<Voto> todosOsVotos = votoService.buscarTodos();// BUSCA TODOS OS VOTOS

		for (Voto vot : todosOsVotos) {// CONTA OS VOTOS POR PAUTAS
			if (vot.getPauta().getId() == codigo) {
				if (vot.getStatus().equals("SIM")) {
					votosSim += 1;
				} else if (vot.getStatus().equals("NAO")) {
					votosNao += 1;
				}
			}
		}
		// ADICONA OS VOTOS NA TELA AO REDIRECIONAR PARA TELA DE RESULTADOS
		redirectAttributes.addFlashAttribute("votosSim", votosSim);
		redirectAttributes.addFlashAttribute("votosNao", votosNao);

		return "redirect:/resultadovotacao";
	}

}
