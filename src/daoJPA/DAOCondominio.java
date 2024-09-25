package daoJPA;

import java.util.List;

import Classes.Condominio;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class DAOCondominio extends DAO<Condominio>{
	
	public Condominio read (Object chave){
		try{
			int id = (int) chave;
			TypedQuery<Condominio> q = manager.createQuery("select c from Condominio c where c.id = :n ", Condominio.class);
			q.setParameter("n", id);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Condominio> readAll(){
		TypedQuery<Condominio> q = manager.createQuery("select c from Condominio c LEFT JOIN FETCH c.moradores order by c.id", Condominio.class);
		return  q.getResultList();
	}
	
// CONSULTAS
	
	public List<Condominio> ocupacaoEdifs(Object chave) {
	    int numMoradores = (int) chave;
	    TypedQuery<Condominio> q = manager.createQuery(
	        "select c from Condominio c join c.moradores m group by c having count(m) = :n", 
	        Condominio.class
	    );
	    q.setParameter("n", numMoradores);

	    return q.getResultList();
	}
	
	public boolean condExiste(Object chave) {
	    String endereco = (String) chave;
	    TypedQuery<Condominio> q = manager.createQuery("select c from Condominio c where c.endereco = :n", Condominio.class);
	    q.setParameter("n", endereco);
	    List<Condominio> resultado = q.getResultList();

	    if(resultado.size() == 0) {
	    	return false;
	    } else {
	    	return true;
	    }
	}
}
