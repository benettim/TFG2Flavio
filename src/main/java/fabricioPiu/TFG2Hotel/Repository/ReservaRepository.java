package fabricioPiu.TFG2Hotel.Repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fabricioPiu.TFG2Hotel.Model.Quarto;
import fabricioPiu.TFG2Hotel.Model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	public Reserva findById(long id); 
	
	
}
