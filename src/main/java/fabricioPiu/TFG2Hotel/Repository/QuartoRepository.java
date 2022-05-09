package fabricioPiu.TFG2Hotel.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fabricioPiu.TFG2Hotel.Model.Quarto;

@Repository
@Transactional
public interface QuartoRepository extends JpaRepository<Quarto, String>{
	
	public Quarto findByNumero(String id);
	
	@Query(value = " select t.* from tb_quarto as t "
			+ "left join tb_reserva as r on t.numero = r.fk_quarto "
			+ " where r.fk_quarto is null or not ((r.data_entrada <= :dt1 and r.data_saida >= :dt1)"
			+ "or (r.data_entrada <= :dt2 and r.data_saida >= :dt2))", 
			nativeQuery=true)
	public List<Quarto>findAllAvailableRooms(@Param("dt1") Date localDate, @Param("dt2") Date localDate2);
	//public List<Quarto>findAllAvailableRooms(@Param("dt1") String localDate, @Param("dt2") String localDate2);
}
