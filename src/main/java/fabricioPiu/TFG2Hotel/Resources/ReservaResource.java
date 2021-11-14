package fabricioPiu.TFG2Hotel.Resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fabricioPiu.TFG2Hotel.Model.Reserva;
import fabricioPiu.TFG2Hotel.Repository.ReservaRepository;

@RestController
public class ReservaResource {

	@Autowired
	private ReservaRepository reservarepository;
	
	@GetMapping("/reservas")
	public List<Reserva> listaReservas(){
		return reservarepository.findAll();
	}
	
	@GetMapping("/reservas/{id}")
	public Reserva listaReservaID(@PathVariable(value="id") long id) {
		return reservarepository.findById(id);
	}
	
	
	@PostMapping("/reservas")
	public Reserva cadastraReserva(@RequestBody Reserva reserva) {
		
		try {
			return reservarepository.save(reserva);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
