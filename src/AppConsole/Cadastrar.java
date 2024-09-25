package AppConsole;

import RegrasNegocio.Fachada;

public class Cadastrar {
	
	public Cadastrar() {
		try {
			Fachada.inicializar();
			System.out.println("--- Cadastrando Condomínios ---");
			Fachada.cadastrarCondominio("João Pessoa", "Rua dos Pessoenses, 5");
			Fachada.cadastrarCondominio("Bayeux", "Rua dos Bayeusenses, 15");
			Fachada.cadastrarCondominio("Santa Rita", "Rua dos Santa-ritenses, 20");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("--- Cadastrando Moradores ---");
			Fachada.cadastrarMorador("Pessoense", "5555");
			Fachada.cadastrarMorador("Bayeuxense", "1515");
			Fachada.cadastrarMorador("Santa-ritense", "2020");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("--- Criando Contratos ---");
			Fachada.criarContrato(1, "5555", 300);
			Fachada.criarContrato(1, "1515", 400);
			Fachada.criarContrato(3, "2020", 500);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
		System.out.print("\nCadastros concluídos!");
	}

	public static void main(String[] args) {
		new Cadastrar();

	}

}
