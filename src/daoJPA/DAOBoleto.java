package daoJPA;

import java.util.List;

import Classes.Boleto;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class DAOBoleto extends DAO<Boleto>{
	
	public Boleto read (Object chave){
		try{
			int codBarras = (int) chave;
			TypedQuery<Boleto> q = manager.createQuery("select b from Boleto b where b.codBarras = :n ", Boleto.class);
			q.setParameter("n", codBarras);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Boleto> readAll(){
		TypedQuery<Boleto> q = manager.createQuery("select b from Boleto b LEFT JOIN FETCH b.condominio JOIN FETCH b.morador order by b.codBarras", Boleto.class);
		return  q.getResultList();
	}
	
// CONSULTAS
	public List<Boleto> boletosNPMorador (Object chave){
		String cpf= (String) chave;
		TypedQuery<Boleto> q = manager.createQuery("select b from Boleto b where b.morador.cpf = :n ", Boleto.class);
		q.setParameter("n", cpf);

		return q.getResultList();
	}
	
	public List<Boleto> boletosNP (Object chave){
		int ano = (int) chave;
		TypedQuery<Boleto> q = manager.createQuery("select b from Boleto b where YEAR(b.data) = :n ", Boleto.class);
		q.setParameter("n", ano);

		return q.getResultList();
	}
}
