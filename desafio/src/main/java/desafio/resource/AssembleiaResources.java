package desafio.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import desafio.model.Assembleia;
import desafio.service.AssembleiaService;

@Controller
public class AssembleiaResources {
	
	@Autowired
	private AssembleiaService assembleiaService;
	
	
	@PostMapping("/cadastroassembleia")
		public String salvarAssembleia(Assembleia assembleia) throws Exception{
		
			 try {
				 assembleiaService.salvarAssembleia(assembleia);
			 }catch (Exception e) {
				throw new Exception(e);
			}
			 return "redirect:/cadastroassembleia";
		    }
	}
			
	

