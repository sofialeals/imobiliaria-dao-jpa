package Classes;
import java.util.ArrayList;
import java.util.List;

public class Condominio {
	private int id;
	private String nome;
	private String endereco;
	private ArrayList<Morador> moradores;
	private ArrayList<String> apartamentos;

	public Condominio(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		this.moradores = new ArrayList<Morador>();
		this.apartamentos = new ArrayList<String>();
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
	
	public String getEndereco() {
		return endereco;
	}
	
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
	
	public ArrayList<String> getApartamentos() {
		return apartamentos;
	}
	
// MÉTODOS DE MORADOR
	
	public void adcMorador(Morador morador) {
		moradores.add(morador);
	}
	
	public boolean rmvMorador(Morador morador) {
		moradores.remove(morador);
		
		return true;
	}
	
	public ArrayList<Morador> getMoradores() {
		return moradores;
	}
}