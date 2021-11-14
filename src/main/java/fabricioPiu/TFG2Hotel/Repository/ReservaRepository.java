package fabricioPiu.TFG2Hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fabricioPiu.TFG2Hotel.Model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	public Reserva findById(long id); 
	
}
