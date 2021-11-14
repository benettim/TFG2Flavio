package fabricioPiu.TFG2Hotel.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_QUARTO")
public class Quarto{
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String numero;
	@Column(nullable=false)
	private float tamanho;
	@Column(nullable=false)
	private String tipo;
	@Column(nullable=false)
	private String opcionais;
	
	private int n_camas;
	
	private float valor;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public float getTamanho() {
		return tamanho;
	}
	public void setTamanho(float tamanho) {
		this.tamanho = tamanho;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getOpcionais() {
		return opcionais;
	}
	public void setOpcionais(String opcionais) {
		this.opcionais = opcionais;
	}
	public int getN_camas() {
		return n_camas;
	}
	public void setN_camas(int n_camas) {
		this.n_camas = n_camas;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
}
