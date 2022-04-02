package fabricioPiu.TFG2Hotel.Controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fabricioPiu.TFG2Hotel.Model.Quarto;
import fabricioPiu.TFG2Hotel.Repository.QuartoRepository;

@Controller
public class QuartoController {

	private QuartoRepository quartoRepository;
	
	public QuartoController(QuartoRepository quartoRepository) {
		this.quartoRepository = quartoRepository;
	}
	
	@GetMapping("/formularioquartos")
	public String quartos(Model model) {
		model.addAttribute("listaQuartos", quartoRepository.findAll());
		return "formularioquartos";
	}
	
	@GetMapping("/formularioquartos/nova")
	public String novoQuarto(@ModelAttribute("quarto") Quarto quarto) {
		return "formq";
	}
	
	@GetMapping("/formularioquartos/{id}")
	public String alterarQuarto(@PathVariable("id") String id, Model model) {
		Optional<Quarto> quartoOpt = Optional.of(quartoRepository.findByNumero(id));
		model.addAttribute("quarto", quartoOpt.get());
		return "formq";
	}
	
	@GetMapping("/formularioquartos/excluir/{id}")
	public String excluirQuarto(@PathVariable("id") String id) {
		Optional<Quarto> quartoOpt = Optional.of(quartoRepository.findByNumero(id));
		quartoRepository.delete(quartoOpt.get());
		return "redirect:/formularioquartos";
	}
	
	@PostMapping("/formularioquartos/salvar")
	public String salvarQuarto(@ModelAttribute("quarto") Quarto quarto) {
		quartoRepository.save(quarto);
		return "redirect:/formularioquartos";
	}
	
}
