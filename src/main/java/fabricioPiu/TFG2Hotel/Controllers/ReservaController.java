package fabricioPiu.TFG2Hotel.Controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import fabricioPiu.TFG2Hotel.Model.Pessoa;
import fabricioPiu.TFG2Hotel.Model.Quarto;
import fabricioPiu.TFG2Hotel.Model.Reserva;
import fabricioPiu.TFG2Hotel.Model.ReservaView;
import fabricioPiu.TFG2Hotel.Repository.PessoaRepository;
import fabricioPiu.TFG2Hotel.Repository.QuartoRepository;
import fabricioPiu.TFG2Hotel.Repository.ReservaRepository;


@Controller
public class ReservaController {

	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private QuartoRepository quartoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	/*public ReservaController(ReservaRepository reservaRepository, QuartoRepository quartoRepository, PessoaRepository pessoaRepository) {
		this.reservaRepository = reservaRepository;
		this.quartoRepository = quartoRepository;
		this.pessoaRepository = pessoaRepository;
	}*/
	
	
	
	/*@GetMapping("/formularioreservas")
	public String reservas(Model model) {
		System.out.println("entoru");
		//model.addAttribute("listareservas", reservaRepository.findAll());
		
		//criar uma lista de ReservaView
		
		for (Reserva reserva : reservaRepository.findAll()) {
			//para cada interação gerar uma instancia de ReservaView
			System.out.println(""+reserva.getData_entrada());
			System.out.println(""+reserva.getData_saida());
			System.out.println(""+reserva.getPessoa().getNome());
			System.out.println(""+reserva.getQuarto().getNumero());
		}
		
		return "formularioreservas";
	}*/
	
	@GetMapping("/formularioreservas")
	public String mostrarReservas(Model model) {
	    List<ReservaView> reservaList = new ArrayList();
		
	    for (Reserva reserva : reservaRepository.findAll()) {
	    	
	    	ReservaView rv = new ReservaView();
	    	rv.setData_entrada(reserva.getData_entrada());
	    	rv.setData_saida(reserva.getData_saida());
	    	rv.setNomePessoa(reserva.getPessoa().getNome());
	    	rv.setNumeroQuarto(reserva.getQuarto().getNumero());
	    	rv.setId(reserva.getId());
	    	reservaList.add(rv);
		}
		
	    model.addAttribute("reservaList", reservaList);
	    
		return "formularioreservas";
	}
	
	
	@GetMapping("/formularioreservas/nova")
	public String novareserva(@ModelAttribute("reserva") Reserva reserva, Model model) {
		model.addAttribute("listaQuartos", quartoRepository.findAll());
		model.addAttribute("listaPessoas", pessoaRepository.findAll());
		return "formr";
	}
	
	@GetMapping("/formularioreservas/{id}")
	public String alterarreserva(@PathVariable("id") long id, Model model) {
		Reserva reserva= reservaRepository.findById(id);
		
		
		//Optional<Reserva> reservaOpt = Optional.of(reservaRepository.findById(id));
		model.addAttribute("reserva", reserva);
		
		
		//model.addAttribute("reserva", reservaOpt.get());
		//model.addAttribute("listaQuartos", reservaRepository.findAllAvailableRooms(reservaOpt.get().getData_entrada(), reservaOpt.get().getData_saida()));
		List<Quarto> quartos = quartoRepository.findAllAvailableRooms(reserva.getData_entrada(), reserva.getData_saida());
		System.out.println(reserva.getData_entrada());
		System.out.println(reserva.getData_saida());
		//Optional.ofNullable(quartos).orElse(Collections.emptyList()).forEach(q -> {System.out.println(q.getNumero());});
		for (Quarto q : quartos) {
			System.out.println(q.getNumero());
		}
		
		model.addAttribute("listaQuartos", quartos );
		
		
		
		//model.addAttribute("listaQuartos", quartoRepository.findAll());
		System.out.println(reserva.getData_entrada());
		model.addAttribute("listaPessoas", pessoaRepository.findAll());
		return "formr";
	}
	
	@GetMapping("/formularioreservas/excluir/{id}")
	public String excluirreserva(@PathVariable("id") long id) {
		Optional<Reserva> reservaOpt = Optional.of(reservaRepository.findById(id));
		reservaRepository.delete(reservaOpt.get());
		return "redirect:/formularioreservas";
	}
	
	@PostMapping("/formularioreservas/salvar")
	public String salvarreserva(@ModelAttribute("reserva") Reserva reserva) {
		System.out.println(reserva.getId());
		reservaRepository.save(reserva);
		return "redirect:/formularioreservas";
	}
}
