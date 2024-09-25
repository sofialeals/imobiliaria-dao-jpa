package RegrasNegocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import Classes.Boleto;
import Classes.Condominio;
import Classes.Morador;
import daoJPA.DAO;
import daoJPA.DAOBoleto;
import daoJPA.DAOCondominio;
import daoJPA.DAOMorador;

public class Fachada {
	private Fachada() {}
	
	private static DAOBoleto daoboleto = new DAOBoleto();
	private static DAOCondominio daocondominio = new DAOCondominio();
	private static DAOMorador daomorador = new DAOMorador();
	
// INICIALIZAÇÃO E FINALIZAÇÃO
	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}
	
// MÉTODOS DO BOLETO
	public static Boleto gerarBoleto(Condominio condominio, Morador morador, String apartamento, double valor) {
		DAO.begin();
		LocalDate data = LocalDate.now();
		int codBarras = Fachada.gerarCodBarras();
		Boleto boleto = Fachada.buscarBoleto(codBarras);
		
		while(boleto != null) {
			codBarras = Fachada.gerarCodBarras();
			boleto = Fachada.buscarBoleto(codBarras);
		}
		
		boleto = new Boleto(codBarras, condominio, morador, data, apartamento, valor);
		daoboleto.create(boleto);
		morador.adcBoleto(boleto);
		daomorador.update(morador);
		DAO.commit();
		return boleto;
	}
	
	public static void excluirBoleto(int codBarras) throws Exception{
		DAO.begin();
		Boleto boleto = Fachada.buscarBoleto(codBarras);
		
		if(boleto == null) {
			DAO.rollback();
			throw new Exception("O boleto de código "+codBarras+" não existe.");
		}
		
		daoboleto.delete(boleto);
		DAO.commit();
	}
	
	public static List<Boleto> listarBoletos(){
		List<Boleto> boletos = daoboleto.readAll();
		return boletos;
	}
	
	public static String exibirTodosOsBoletos() {
		List<Boleto> boletos = Fachada.listarBoletos();
		String boletosFormatado = "";
		
		if(boletos.size()== 0) {
			return "Ainda não há boletos cadastrados.";
		}
		
		for(Boleto b : boletos) {
			boletosFormatado += b + "\n\n";
		}
		
		return boletosFormatado;
	}
	
	public static boolean pagarBoleto(int codBoleto) throws Exception{
		DAO.begin();
		Boleto boleto = Fachada.buscarBoleto(codBoleto);
		
		if(boleto == null) {
			DAO.rollback();
			throw new Exception("O boleto de código "+codBoleto+" não existe.");
		}
		
		Morador morador = boleto.getMorador();
		List<Boleto> boletosNP = Fachada.boletosNPMorador(morador.getCpf());
		if(boletosNP.size() >= 3) {
			DAO.rollback();
			throw new Exception("Existem 3 ou mais boletos em atraso. Entre em acordo para conseguir pagar o atual.");
		}
		
		if(boleto.getPagou() == true) {
			DAO.rollback();
			throw new Exception("O boleto de código "+codBoleto+" já foi pago.");
		}
		
		boleto.pagar();
		daoboleto.update(boleto);
		DAO.commit();
		
		return true;
	}
	
	public static List<Boleto> boletosNP(int ano){
		List<Boleto> boletosNP = daoboleto.boletosNP(ano);
		return boletosNP;
	}
	
	public static String exibirBoletosNP(int ano) {
		List<Boleto> boletosNP = Fachada.boletosNP(ano);
		String boletosNPFormatado = "";
		
		if(boletosNP.size() == 0) {
			return "Não há boletos atrasados no ano de "+ano+".";
		}
		
		for(Boleto b : boletosNP) {
			boletosNPFormatado += b + "\n\n";
		}
		
		return boletosNPFormatado;
	}
	
	public static List<Boleto> boletosNPMorador(String cpf){
		List<Boleto> boletosNPMorador = daoboleto.boletosNPMorador(cpf);
		return boletosNPMorador;
	}
	
	public static Boleto buscarBoleto(int codBarras) {
		Boleto boleto = daoboleto.read(codBarras);
		
		return boleto;
	}
	
	public static int gerarCodBarras() {
		Random random = new Random();
        int codBarras = random.nextInt(100000);;
        
        int tam = (String.valueOf(codBarras)).length();
        
        if (tam < 5) {
            int complemento = 5 - tam;
            String numFormatado = "0".repeat(complemento) + String.valueOf(codBarras);
            codBarras = Integer.parseInt(numFormatado);
        }
        
        return codBarras;
	}
	
// MÉTODOS DO CONDOMÍNIO
	public static Condominio cadastrarCondominio(String nome, String endereco) throws Exception{
		DAO.begin();
		
		if(nome.equals("") || endereco.equals("")) {
			DAO.rollback();
			throw new Exception("Existem campos vazios. Preencha-os e tente novamente.");
		}
		
		boolean cadastrado = daocondominio.condExiste(endereco);
		if(cadastrado) {
			DAO.rollback();
			throw new Exception("Um condomínio com o endereço "+endereco+" já está cadastrado.");
		}
		
		Condominio condominio = new Condominio(nome, endereco);
		daocondominio.create(condominio);
		DAO.commit();
		return condominio;
	}
	
	public static void excluirCondominio(int id) throws Exception{
		DAO.begin();
		
		Condominio condominio = Fachada.buscarCondominio(id);
		
		if(condominio == null) {
			DAO.rollback();
			throw new Exception("O condomínio de ID "+id+" não está cadastrado.");
		} else if(condominio.getMoradores().size() > 0) {
			DAO.rollback();
			throw new Exception("O condomínio não pode ser excluído porque ainda tem moradores registrados.");
		}
		
		daocondominio.delete(condominio);
		DAO.commit();
	}
	
	public static List<Condominio> listarCondominios(){
		List<Condominio> condominios = daocondominio.readAll();
		return condominios;
	}
	
	public static String exibirCondominios() {
		List<Condominio> condominios = Fachada.listarCondominios();
		String condominiosFormatado = "";
		
		if(condominios.size() == 0) {
			return "Não há condomínios cadastrados.";
		}
		
		for(Condominio c : condominios) {
			condominiosFormatado += c + "\n\n";
		}
		
		return condominiosFormatado;
	}
	
	public static List<Condominio> ocupacaoEdifs(int numMoradores){
		List<Condominio> ocupacaoEdifs = daocondominio.ocupacaoEdifs(numMoradores);
		return ocupacaoEdifs;
	}
	
	public static String exibirOcupEdifs(int numMoradores) throws Exception{
		List<Condominio> condominios = Fachada.ocupacaoEdifs(numMoradores);
		String condominiosFormatado = "";
		
		if(condominios.size() == 0) {
			throw new Exception("Não há condomínios com "+numMoradores+" moradores.");
		}
		
		for(Condominio c : condominios) {
			condominiosFormatado += c + "\n\n";
		}
		
		return condominiosFormatado;
	}
	
	public static Condominio buscarCondominio(int id) {
		Condominio condominio = daocondominio.read(id);
		
		return condominio;
	}

// MÉTODOS DO MORADOR
	public static Morador cadastrarMorador(String nome, String cpf) throws Exception{
		DAO.begin();
		Morador morador = Fachada.buscarMorador(cpf);
		if(morador != null) {
			DAO.rollback();
			throw new Exception("O morador de CPF "+cpf+" já está cadastrado.");
		}
		
		if(nome.equals("") || cpf.equals("")) {
			DAO.rollback();
			throw new Exception("Existem campos vazios. Preencha-os e tente novamente.");
		}
		
		morador = new Morador(nome, cpf);
		daomorador.create(morador);
		DAO.commit();
		return morador;
	}
	
	public static void excluirMorador(String cpf) throws Exception{
		DAO.begin();
		Morador morador = Fachada.buscarMorador(cpf);
		
		if(morador == null) {
			DAO.rollback();
			throw new Exception("O morador de CPF "+cpf+" não está cadastrado.");
		}
		
		if(morador.getCondominios().size() > 0) {
			for(Condominio c : morador.getCondominios()) {
				c.rmvMorador(morador);
			}
		}
		
		if(morador.getBoletos().size() > 0) {
			for(Boleto b : morador.getBoletos()) {
				if(b.getPagou() == false) {
					DAO.rollback();
					throw new Exception("O morador "+morador.getNome()+" não pode ser excluído porque ainda existem boletos pendentes no seu nome.");
				}
				daoboleto.delete(b);
			}
		}
		
		daomorador.delete(morador);
		DAO.commit();
	}
	
	public static List<Morador> listarMoradores(){
		List<Morador> moradores = daomorador.readAll();
		
		return moradores;
	}
	
	public static List<Morador> inadsCondX(int idCond){
		List<Morador> inadsCondX = daomorador.inadsCondX(idCond);
		return inadsCondX;
	}
	
	public static String exibirInadsCondX(int idCond) throws Exception{
		Condominio condominio = Fachada.buscarCondominio(idCond);
		if(condominio == null) {
			throw new Exception("Não existe um condomínio com o código "+idCond+". Digite um código válido.");
		}
		
		List<Morador> inads = Fachada.inadsCondX(idCond);
		String inadsFormatado = "";
		
		if(inads.size() == 0) {
			throw new Exception("Não há moradores inadimplentes no condomínio.");
		}
		
		for(Morador m : inads) {
			inadsFormatado += m + "\n\n";
		}
		
		return inadsFormatado;
	}
	
	public static Morador buscarMorador(String cpf){
		Morador morador = daomorador.read(cpf);
		
		return morador;
	}
	
	public static String exibirMoradores() {
		List<Morador> moradores = Fachada.listarMoradores();
		String moradoresFormatado = "";
		
		if(moradores.size() == 0) {
			return "Não há moradores.";
		}
		
		for(Morador m : moradores) {
			moradoresFormatado += m + "\n\n";
		}
		
		return moradoresFormatado;
	}
	
// REGRAS DE NEGÓCIO
	
	public static void criarContrato(int idCond, String cpfMorador, double valorAluguel) throws Exception{
		DAO.begin();
		
		Condominio condominio = Fachada.buscarCondominio(idCond);
		if(condominio == null) {
			DAO.rollback();
			throw new Exception("O condomínio de ID "+idCond+" não existe.");
		}
		Morador morador = Fachada.buscarMorador(cpfMorador);
		if(morador == null) {
			DAO.rollback();
			throw new Exception("O morador de CPF "+cpfMorador+" não existe.");
		}
		
		String numAp = "";
		
		for(String ap : condominio.getApartamentos()) {
			if(ap.contains("disponivel")) {
				String[] divisao = ap.split("/");
				numAp = divisao[0];
				
				String apCond = numAp+"/"+cpfMorador;
				condominio.adcApartamento(apCond);
				
				String apMorador = numAp+"/"+idCond;
				morador.adcApartamento(apMorador);
				
				condominio.rmvApartamento(ap);
				break;
			}
		}
		
		if(numAp.equals("")) {
			numAp = condominio.getApartamentos().size()+1+"";
			
			String apCond = numAp+"/"+cpfMorador;
			condominio.adcApartamento(apCond);
			
			String apMorador = numAp+"/"+idCond;
			morador.adcApartamento(apMorador);
		}
		
		if(condominio.getMoradores().contains(morador)) {
			daomorador.update(morador);
		} else {
			condominio.adcMorador(morador);
			morador.adcCondominio(condominio);
			
			daocondominio.update(condominio);
			daomorador.update(morador);
		}

		Fachada.gerarBoleto(condominio, morador, numAp, valorAluguel);
		
		DAO.commit();
	}
	
	public static void encerrarContrato(int idCond, String cpfMorador, String numAp) throws Exception{
		DAO.begin();
		
		Condominio condominio = Fachada.buscarCondominio(idCond);
		if(condominio == null) {
			DAO.rollback();
			throw new Exception("O condomínio de ID "+idCond+" não existe.");
		}
		Morador morador = Fachada.buscarMorador(cpfMorador);
		if(morador == null) {
			DAO.rollback();
			throw new Exception("O morador de CPF "+cpfMorador+" não existe.");
		}
		
		String apMorador = numAp+"/"+idCond;
		String apCond = numAp+"/"+cpfMorador;
		
		if(condominio.getMoradores().contains(morador)) {
			for(String ap : morador.getApartamentos()) {
				if(ap.equals(apMorador)) {
					morador.rmvApartamento(ap);
					morador.rmvCondominio(condominio);
				}
				break;
			}
			
			for(String ap : condominio.getApartamentos()) {
				if(ap.equals(apCond)) {
					condominio.rmvApartamento(ap);
					String disponivel = numAp+"/"+"disponivel";
					condominio.adcApartamento(disponivel);
					condominio.rmvMorador(morador);
				}
				break;
			}
		} else {
			DAO.rollback();
			throw new Exception("O morador de CPF "+cpfMorador+" não mora no condomínio.");
		}
		
		daocondominio.update(condominio);
		daomorador.update(morador);
		
		DAO.commit();
	}
	
	public static boolean fazerAcordo(String cpfMorador) throws Exception{
		if(cpfMorador.equals("")) {
			throw new Exception("O campo CPF está vazio.");
		}
		
		Morador morador = Fachada.buscarMorador(cpfMorador);

		if(morador == null) {
			throw new Exception("O morador de CPF "+cpfMorador+" não existe.");
		}
		
		List<Boleto> boletosNaoPagos = daoboleto.boletosNPMorador(cpfMorador);
		if(boletosNaoPagos.size() < 3) {
			throw new Exception("O morador ainda não precisa fazer um acordo.");
		}
		
		for(Boleto b : boletosNaoPagos) {
			Fachada.pagarBoleto(b.getCodBarras());
		}
		
		return true;
	}
	
	public static int adcBoletos() {
		int qntBoletos = 0;
		LocalDate dataAtual = LocalDate.now();
		if(Fachada.listarMoradores().size() > 0) {
			for(Morador m : Fachada.listarMoradores()) {
				if(m.getBoletos().size() > 0) {
					for(Boleto b : m.getBoletos()) {
						if(m.getCondominios().contains(b.getCondominio())) {
							if(dataAtual.getMonthValue() > b.getData().getMonthValue() || (dataAtual.getMonthValue() == 1 && b.getData().getMonthValue() == 12)) {
								Fachada.gerarBoleto(b.getCondominio(), m, b.getApartamento(), b.getValor());
								qntBoletos += 1;
							}
						}
					}
				}
			}
		}
		return qntBoletos;
	}
	
	public static double valorAtrasados(String cpf){
		List<Boleto> boletosNPMorador = daoboleto.boletosNPMorador(cpf);
		double valorTotal = 0;
		
		for(Boleto b : boletosNPMorador){
			valorTotal += b.getValor();
		}
		
		return valorTotal;
	}
}