package fabricioPiu.TFG2Hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fabricioPiu.TFG2Hotel.Model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	public Pessoa findById(long id);
	public Pessoa findByCpf(String CPF);
	public Pessoa findByNome(String nome);
}
