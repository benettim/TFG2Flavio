package fabricioPiu.TFG2Hotel.Model;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_RESERVA")
public class Reserva {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private Date data_entrada;
	@Column(nullable=false)
	private Date data_saida;
	@Column(nullable=false)
	private long fk_pessoa;
	@Column(nullable=false)
	private String fk_quarto;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getData_entrada() {
		return data_entrada;
	}
	public void setData_entrada(Date data_entrada) {
		this.data_entrada = data_entrada;
	}
	public Date getData_saida() {
		return data_saida;
	}
	public void setData_saida(Date data_saida) {
		this.data_saida = data_saida;
	}
	public long getFk_pessoa() {
		return fk_pessoa;
	}
	public void setFk_pessoa(long fk_pessoa) {
		this.fk_pessoa = fk_pessoa;
	}
	public String getFk_quarto() {
		return fk_quarto;
	}
	public void setFk_quarto(String fk_quarto) {
		this.fk_quarto = fk_quarto;
	}
	
	
}
