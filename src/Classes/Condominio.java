package Classes;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "condominio")
public class Condominio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String endereco;
	
	@ManyToMany(mappedBy = "condominios")
	private List<Morador> moradores = new ArrayList<>();
	
	@ElementCollection
	private List<String> apartamentos = new ArrayList<>();
	
	public Condominio(){}
	public Condominio(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int novoID) {
		this.id = novoID;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "\n- Condomínio "+nome+" | ID: "+id+
				"\nEndereço: "+endereco;
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
	
// MÉTODOS DE MORADOR
	
	public void adcMorador(Morador morador) {
		moradores.add(morador);
	}
	
	public boolean rmvMorador(Morador morador) {
		moradores.remove(morador);
		
		return true;
	}
	
	public List<Morador> getMoradores() {
		return moradores;
	}
	
	public void setMoradores(ArrayList<Morador> moradores) {
		this.moradores = moradores;
	}
}