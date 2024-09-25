package daoJPA;

import java.time.LocalDate;
import java.util.List;

import Classes.Morador;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class DAOMorador extends DAO<Morador>{
	
	public Morador read (Object chave){
		try{
			String cpf = (String) chave;
			TypedQuery<Morador> q = manager.createQuery("select m from Morador m where m.cpf = :n ", Morador.class);
			q.setParameter("n", cpf);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Morador> readAll(){
		TypedQuery<Morador> q = manager.createQuery("select m from Morador m LEFT JOIN FETCH m.condominios order by m.nome", Morador.class);
		return  q.getResultList();
	}
	
// CONSULTAS
	public List<Morador> inadsCondX(Object chave) {
	    int idCond = (int) chave;
	    int anoAtual = LocalDate.now().getYear();

	    TypedQuery<Morador> q = manager.createQuery("select distinct m from Morador m join m.boletos b join m.condominios c "+
	    											"where b.pagou = false and c.id = :idCond and YEAR(b.data) = :anoAtual", Morador.class);
	    q.setParameter("idCond", idCond);
	    q.setParameter("anoAtual", anoAtual);

	    return q.getResultList();
	}
}
