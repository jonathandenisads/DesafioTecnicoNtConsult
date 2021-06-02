package desafio.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		return "index";	
	}
	@RequestMapping("/associado")
	public String buscarTelaInicioAssociado() {
		return "telainicioassociado";	
	}
	@RequestMapping("/administrador")
	public String buscarTelaInicioAdmin() {
		return "telainicioadministrador";	
	}
	@RequestMapping("/cadastroassembleia")
	public String cadastrarAssembleia() {
		return "telacadastroassembleia";	
	}
	@RequestMapping("/principalassociado")
	public String buscarprincipalAssociado() {
		return "telaprincipalassociado";	
	}
	@RequestMapping("/unablevote")
	public String negarVotacao() {
		return "telanegarvotacao";	
	}
	@RequestMapping("/realizarvotacao")
	public String realizarVotacao() {
		return "telavotacao";	
	}
	@RequestMapping("/resultadovotacao")
	public String buscarResultadoVotacao() {
		return "telaresultadovotacao";	
	}
	
	

			

}
