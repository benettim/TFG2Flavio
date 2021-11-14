package fabricioPiu.TFG2Hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fabricioPiu.TFG2Hotel.Model.Quarto;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, String>{
	
	public Quarto findByNumero(String id);
	
}
