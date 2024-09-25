package Classes;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Boleto {
	
	@Id
	private int codBarras;
	private String apartamento;
	private Condominio condominio;
	private Morador morador;
	private LocalDate data;
	private double valor;
	private boolean pagou;
		
	public Boleto() {}
	public Boleto(int codBarras, Condominio condominio, Morador morador, LocalDate data, String apartamento, double valor) {
		this.codBarras = codBarras;
		this.condominio = condominio;
		this.morador = morador;
		this.data = data;
		this.apartamento = apartamento;
		this.valor = valor;
		this.pagou = false;
	}

	public boolean getPagou() {
		return pagou;
	}
	
	public void pagar() {
		this.pagou = true;
	}
	
	public int getCodBarras() {
		return codBarras;
	}
	
	public void setCodBarras(int novoCodBarras) {
		this.codBarras = novoCodBarras;
	}
	
	public String getApartamento() {
		return apartamento;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public Morador getMorador() {
		return morador;
	}

	public LocalDate getData() {
		return data;
	}

	public double getValor() {
		return valor;
	}
	
	public String toString() {
		return "\n"+codBarras+
				"\nPagador: "+morador.getNome()+
				"\nData: "+data+
				"\nValor: "+valor+
				"\nCondomínio: "+condominio+
				"\nApartamento: "+apartamento;
	}
}
