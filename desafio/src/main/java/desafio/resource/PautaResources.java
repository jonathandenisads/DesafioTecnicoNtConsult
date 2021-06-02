package desafio.resource;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import desafio.model.Assembleia;
import desafio.model.Pauta;
import desafio.service.AssembleiaService;
import desafio.service.PautaService;

@Controller
public class PautaResources {
	
	@Autowired
	private PautaService pautaService;
	

	@Autowired
	private AssembleiaService assembleiaService;

	@GetMapping("/listar")
	public ModelAndView listaAssembleia(){
		ModelAndView mv = new ModelAndView("telacadastropauta");
		 Iterable<Assembleia> assembleias= assembleiaService.buscarAssembleias();
		 mv.addObject("pauta", new Pauta());
		 mv.addObject("assembleias", assembleias);
		 return mv;
	}
	
	 @PostMapping("/listar")
	    public String criar(Pauta pauta) throws Exception {
		 try {
		 pautaService.salvarPauta(pauta);
		 }catch (Exception e) {
			throw new Exception(e);
		}
		 return "redirect:/listar";
	    }
	 
	
	 
}
