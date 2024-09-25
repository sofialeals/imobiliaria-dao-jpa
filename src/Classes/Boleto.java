package Classes;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Boleto {
	
	@Id
	private int codBarras;
	private String apartamento;
	
	@ManyToOne
	@JoinColumn(name="ref_condominio")
	private Condominio condominio;
	
	@ManyToOne
	@JoinColumn(name="ref_morador")
	private Morador morador;
	private LocalDate data;
	private double valor;
	private boolean pagou;
		
	public Boleto(){}
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

	public void setPagou(boolean pagou) {
		this.pagou = pagou;
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
	
	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

	public Condominio getCondominio() {
		return condominio;
	}
	
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public Morador getMorador() {
		return morador;
	}
	
	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return "\n"+codBarras+
				"\nPagador: "+morador.getNome()+
				"\nData: "+data+
				"\nValor: "+valor+
				"\nCondomínio: "+condominio+
				"\nApartamento: "+apartamento;
	}
}
