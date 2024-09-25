package AppConsole;

import Classes.Boleto;
import Classes.Condominio;
import Classes.Morador;
import RegrasNegocio.Fachada;

public class Listar {
	
	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("-------- Listagem de Boletos --------");
			for(Boleto b : Fachada.listarBoletos()) {
				System.out.println(b);
			}
			
			System.out.println("\n-------- Listagem de Condomínios --------");
			for(Condominio c : Fachada.listarCondominios()) {
				System.out.println(c);
			}
			
			System.out.println("\n-------- Listagem de Moradores --------");
			for(Morador m : Fachada.listarMoradores()) {
				System.out.println(m);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
	}

	public static void main(String[] args) {
		new Listar();

	}

}
