package fabricioPiu.TFG2Hotel.Controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import fabricioPiu.TFG2Hotel.Model.Pessoa;
import fabricioPiu.TFG2Hotel.Model.Quarto;
import fabricioPiu.TFG2Hotel.Repository.PessoaRepository;
import fabricioPiu.TFG2Hotel.Resources.PessoaResource;
import fabricioPiu.TFG2Hotel.Resources.QuartoResource;

@Controller
public class WebController {
	
	@Autowired
	PessoaResource pessoaresource;
	@Autowired
	QuartoResource quartoresource;
	
	@RequestMapping("/")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	//lista as pessoas cadastradas em localhost:8080/pessoas
	@GetMapping("/pessoas")
	public ModelAndView showUserList(Pessoa pessoa) {
	    
		
		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<List<Pessoa>> response = 
				rest.exchange(
				"http://localhost:8080/api/pessoas", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Pessoa>>() {}
				);
		
		List<Pessoa> pessoas = response.getBody();
		
		for (Pessoa pessoax : pessoas) {
			System.out.println(pessoax.getNome());
		}
		
		
		ModelAndView view = new ModelAndView("pessoas");
		view.addObject("pessoas", pessoas);
		
	    return view;
	}
	
	//DELETE USER
	/*private void deleteUser() {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("cpf", "1");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, params);
    }*/
	
	
	@PostMapping("/pessoas")
	public ModelAndView postPessoa(@ModelAttribute Pessoa pessoa, @RequestParam(value = "action", required=true) String action) {
	    
		
		System.out.println(action);
		//System.out.println(pessoa.getNome());
		
//		String dados[] = StringUtils.split(action, "|");
		String dados[] = action.split("\\|");
		
		for (int i = 0; i < dados.length; i++) 
		{
			System.out.println(dados[i]);
		}
		
		if(dados[0].equals("editar")) {
			Map < String, String > params = new HashMap < String, String > ();
	        params.put("id", "1");
	        Pessoa updatedUser = new Pessoa();
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.put("http://localhost:8080/api/pessoas/", updatedUser, params);
		}


		else if (dados[0].equals("excluir"))
		{
			System.out.println("excluindo o " + dados[1]);
			
			
			/*RestTemplate rest = new RestTemplate();
			
			MultiValueMap<String, String> parametros = new LinkedMultiValueMap<String, String>();
			parametros.add("cpf", dados[1]);
			
			rest.delete("http://localhost:8080/api/pessoas", parametros, Pessoa.class);*/

			/*Map<String, String > params = new HashMap<String, String>();
	        params.put("cpf", dados[1]);
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.delete("http://localhost:8080/api/pessoas", params);*/
			
			Map < String, String > params = new HashMap < String, String > ();
	        params.put("cpf", dados[1]);
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.delete("http://localhost:8080/api/pessoas/{cpf}", params);
			
		}
		
		/*RestTemplate rest = new RestTemplate();
		
		ResponseEntity<List<Pessoa>> response = 
				rest.exchange(
				"http://localhost:8080/api/pessoas", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Pessoa>>() {}
				);
		
		List<Pessoa> pessoas = response.getBody();*/
		
		/*for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.getNome());
		}
		*/
		
		ModelAndView view = new ModelAndView("pessoas");
		//view.addObject("pessoas", pessoas);
		
	    return view;
	}
	
	@GetMapping("/formulario")
    public String showform(Pessoa pessoa){
        
        return "formulario";
    }
    
	//formulario de cadastro de pessoas em localhost:8080/formulario
    @PostMapping("/formulario")
    public String cadastrarPessoa(Pessoa pessoa){
    	System.out.println(pessoa.getNome());
        
    	RestTemplate rest = new RestTemplate();
    	
    	ResponseEntity<Pessoa> pessoaSalva = rest.postForEntity("http://localhost:8080/api/pessoas", pessoa, Pessoa.class);
    	
    	System.out.println(pessoaSalva.getBody().getNome());
    	
    	
        return "index";
    }
    
    //lista os quartos cadastrados em localhost:8080/quartos
    @RequestMapping("/quartos")
	public ModelAndView showQuartosList(Model model) {
	    
		
		RestTemplate rest = new RestTemplate();
		
		
		ResponseEntity<List<Quarto>> response = 
				rest.exchange(
				"http://localhost:8080/api/quartos", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Quarto>>() {}
				);
		
		List<Quarto> quartos = response.getBody();
		
		for (Quarto quarto : quartos) {
			System.out.println(quarto.getNumero());
		}
		
		
		ModelAndView view = new ModelAndView("quartos");
		view.addObject("quartos", quartos);
		
	    return view;
	}
    
    /*@GetMapping("/formularioquartos")
    public String showform(Quarto quarto){
        
        return "formularioquartos";
    }
    
    //formulario de cadastro de quartos em localhost:8080/formularioquartos
    @PostMapping("/formularioquartos")
    public String cadastrarQuarto(Quarto quarto){
    	
    	RestTemplate rest = new RestTemplate();
    	
    	ResponseEntity<Quarto> quartoSalvo = rest.postForEntity("http://localhost:8080/api/quartos", quarto, Quarto.class);
    	
    	System.out.println(quartoSalvo.getBody().getNumero());
    	
        return "index";
    }*/
	
	
}
