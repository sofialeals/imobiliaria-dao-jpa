package Classes;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "morador")
public class Morador {
	private String nome;
	@Id
	private String cpf;
	
	@ElementCollection
	private List<String> apartamentos = new ArrayList<>();
	
	@ManyToMany
    @JoinTable(
        name = "morador_condominio",
        joinColumns = @JoinColumn(name = "morador_cpf"),
        inverseJoinColumns = @JoinColumn(name = "condominio_id")
    )
	private List<Condominio> condominios = new ArrayList<>();
	
	@OneToMany(mappedBy="morador")
	private List<Boleto> boletos = new ArrayList<>();

	public Morador(){}
	public Morador(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.apartamentos = new ArrayList<String>();
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
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
	
	public List<Boleto> getBoletos(){
		return boletos;
	}
	
	public void setBoletos(ArrayList<Boleto> boletos) {
		this.boletos = boletos;
	}

// MÉTODOS DE CONDOMÍNIO
	
	public void adcCondominio(Condominio condominio) {
		condominios.add(condominio);
	}
	
	public void rmvCondominio(Condominio condominio) {
		condominios.remove(condominio);
	}

	public List<Condominio> getCondominios() {
		return condominios;
	}
	
	public void setCondominios(ArrayList<Condominio> condominios) {
		this.condominios = condominios;
	}
	
// MÉTODOS DE APARTAMENTO
	
	public void adcApartamento(String apartamento) {
		apartamentos.add(apartamento);
	}
	
	public void rmvApartamento(String apartamento) {
		apartamentos.remove(apartamento);
	}

	public List<String> getApartamentos() {
		return apartamentos;
	}
	
	public void setApartamentos(ArrayList<String> apartamentos) {
		this.apartamentos = apartamentos;
	}
}