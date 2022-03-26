package fabricioPiu.TFG2Hotel.Resources;

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

import fabricioPiu.TFG2Hotel.Model.Quarto;
import fabricioPiu.TFG2Hotel.Repository.QuartoRepository;


@RestController
@RequestMapping(value="/api")
public class QuartoResource {
	
	@Autowired
	private QuartoRepository quartorepository;
	
	@GetMapping("/quartos")
	public List<Quarto> listaQuartos(){
		return quartorepository.findAll();
	}
	
	@GetMapping("/quartos/{id}")
	public Quarto listaQuartoID(@PathVariable(value="id") String id){
		return quartorepository.findByNumero(id);
	}
	
	@PostMapping("/quartos")
	public Quarto cadastraQuarto(@RequestBody Quarto quarto) {
		System.out.println("tentando cadastrar quarto");
		try {
			return quartorepository.save(quarto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@DeleteMapping("/quartos")
	public void deletaQuartos(@RequestBody Quarto quarto){
		quartorepository.delete(quarto);
	}
	
	@PutMapping("/quartos")
	public Quarto atualizaQuartos(@RequestBody Quarto quarto){
		return quartorepository.save(quarto);
	}
	
}
