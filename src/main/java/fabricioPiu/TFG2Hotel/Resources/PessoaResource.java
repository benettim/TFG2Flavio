package fabricioPiu.TFG2Hotel.Resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fabricioPiu.TFG2Hotel.Model.Pessoa;
import fabricioPiu.TFG2Hotel.Repository.PessoaRepository;

@RestController
@RequestMapping("/api")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoarepository;
	
	@GetMapping("/pessoas")
	public List<Pessoa> listaPessoas(){
		
		return pessoarepository.findAll();
	}
	
	@GetMapping("/pessoas/{id}")
	public Pessoa listaPessoaID(@PathVariable(value="id") long id) {
		return pessoarepository.findById(id);
	}
	
	@GetMapping("/pessoasCPF/{cpf}")
	public Pessoa listaPessoaCPF(@PathVariable(value="cpf") String CPF) {
		return pessoarepository.findByCpf(CPF);
	}
	
	@PostMapping("/pessoas")
	public Pessoa cadastraPessoa(@RequestBody Pessoa pessoa) {
		System.out.println("tentando cadastrar pessoa");
		try {
			return pessoarepository.save(pessoa);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/*@DeleteMapping("/pessoas/{cpf}")
	public void deletaPessoa(@PathVariable(value="cpf") String cpf) {
		Pessoa pessoa = pessoarepository.findByCpf(cpf);
		pessoarepository.delete(pessoa);
	}*/
	
	/*@DeleteMapping("/pessoas")
	public void deletaPessoa(@RequestBody Pessoa pessoa) {
		pessoarepository.delete(pessoa);
	}*/
	
	@DeleteMapping("/pessoas/{cpf}")
	public void deletaPessoa(@PathVariable(value = "cpf") String cpf) {
		Pessoa pessoa = pessoarepository.findByCpf(cpf);
		pessoarepository.delete(pessoa);
	}
	
	@PutMapping("/pessoas")
	public Pessoa atualizaPessoa(@RequestBody Pessoa pessoa) {
		return pessoarepository.save(pessoa);
	}
	
}
