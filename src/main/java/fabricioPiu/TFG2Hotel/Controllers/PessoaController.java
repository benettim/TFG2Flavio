package fabricioPiu.TFG2Hotel.Controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fabricioPiu.TFG2Hotel.Model.Pessoa;
import fabricioPiu.TFG2Hotel.Repository.PessoaRepository;

@Controller
public class PessoaController {

	private PessoaRepository pessoaRepository;
	
	public PessoaController(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	@GetMapping("/formulariopessoas")
	public String pessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaRepository.findAll());
		return "formulariopessoas";
	}
	
	@GetMapping("/formulariopessoas/nova")
	public String novaPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		return "form";
	}
	
	@GetMapping("/formulariopessoas/{id}")
	public String alterarPessoa(@PathVariable("id") long id, Model model) {
		Optional<Pessoa> pessoaOpt = Optional.of(pessoaRepository.findById(id));
		model.addAttribute("pessoa", pessoaOpt.get());
		return "form";
	}
	
	@GetMapping("/formulariopessoas/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") long id) {
		Optional<Pessoa> pessoaOpt = Optional.of(pessoaRepository.findById(id));
		pessoaRepository.delete(pessoaOpt.get());
		return "redirect:/formulariopessoas";
	}
	
	@PostMapping("/formulariopessoas/salvar")
	public String salvarPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return "redirect:/formulariopessoas";
	}
}
