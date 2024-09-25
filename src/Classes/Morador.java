package Classes;
import java.util.ArrayList;
import java.util.List;

public class Morador {
	private String nome;
	private String cpf;
	private ArrayList<String> apartamentos;
	private ArrayList<Condominio> condominios;
	private ArrayList<Boleto> boletos;

	public Morador(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.apartamentos = new ArrayList<String>();
		this.condominios = new ArrayList<Condominio>();
		this.boletos = new ArrayList<Boleto>();
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
	
	public String toString() {
		String condsImprimir = "";
		
		for(Condominio c : condominios) {
			condsImprimir += c.toString();
		}
		
		return "\nNome: "+nome+" | CPF: "+cpf+
				"\nCondomínios:"+condsImprimir;
	}
	
// MÉTODOS DE BOLETO
	
	public void adcBoleto(Boleto boleto) {
		boletos.add(boleto);
	}
	
	public void rmvBoleto(Boleto boleto) {
		boletos.remove(boleto);
	}
	
	public ArrayList<Boleto> getBoletos(){
		return boletos;
	}

// MÉTODOS DE CONDOMÍNIO
	
	public void adcCondominio(Condominio condominio) {
		condominios.add(condominio);
	}
	
	public void rmvCondominio(Condominio condominio) {
		condominios.remove(condominio);
	}

	public ArrayList<Condominio> getCondominios() {
		return condominios;
	}
	
// MÉTODOS DE APARTAMENTO
	
	public void adcApartamento(String apartamento) {
		apartamentos.add(apartamento);
	}
	
	public void rmvApartamento(String apartamento) {
		apartamentos.remove(apartamento);
	}

	public ArrayList<String> getApartamentos() {
		return apartamentos;
	}
}