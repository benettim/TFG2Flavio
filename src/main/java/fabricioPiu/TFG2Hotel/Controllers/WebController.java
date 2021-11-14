package fabricioPiu.TFG2Hotel.Controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


import fabricioPiu.TFG2Hotel.Model.Pessoa;
import fabricioPiu.TFG2Hotel.Resources.PessoaResource;

@Controller
public class WebController {
	
	@Autowired
	PessoaResource pessoaresource;
	
	@RequestMapping("/")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	@RequestMapping("/pessoas")
	public ModelAndView showUserList(Model model) {
	    
		
		RestTemplate rest = new RestTemplate();
		//ResponseEntity<Pessoa> pessoas = rest.getForEntity("http://localhost:8080/api/pessoas", Pessoa.class);
		//pessoas = rest.getForEntity("http://localhost:8080/api/pessoas", Pessoa.class);
		
		
		
		ResponseEntity<List<Pessoa>> response = 
				rest.exchange(
				"http://localhost:8080/api/pessoas", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Pessoa>>() {}
				);
		
		List<Pessoa> pessoas = response.getBody();
		
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.getNome());
		}
		
		
		ModelAndView view = new ModelAndView("pessoas");
		view.addObject("pessoas", pessoas);
		
	    return view;
	}
	
	@GetMapping("/formulario")
    public String showform(Pessoa pessoa){
        
        return "formulario";
    }
    
    @PostMapping("/formulario")
    public String cadastrarPessoa(Pessoa pessoa){
    	System.out.println(pessoa.getNome());
        
    	RestTemplate rest = new RestTemplate();
    	
    	ResponseEntity<Pessoa> pessoaSalva = rest.postForEntity("http://localhost:8080/api/pessoas", pessoa, Pessoa.class);
    	
    	System.out.println(pessoaSalva.getBody().getNome());
    	
    	
        return "index";
    }
	
	
}
