package AppConsole;

import Classes.Boleto;
import Classes.Condominio;
import Classes.Morador;
import RegrasNegocio.Fachada;

public class Consultar {
	
	public Consultar() {
		try {
			Fachada.inicializar();
			System.out.print("Consultas:\n");
			System.out.print("-------- Boletos não pagos no ano de 2024 --------");
			for(Boleto b : Fachada.boletosNP(2024)) {
				System.out.println(b);
			}
			
			System.out.print("\n-------- Inadimplentes do Condomínio João Pessoa --------");
			for(Morador m : Fachada.inadsCondX(1)) {
				System.out.println(m);
			}
			
			System.out.print("\n-------- Edifícios com 2 moradores --------");
			for(Condominio c : Fachada.ocupacaoEdifs(2)) {
				System.out.println(c);
			}
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		Fachada.finalizar();
		System.out.print("\nConsultas finalizadas!");
	}

	public static void main(String[] args) {
		new Consultar();
	}

}
