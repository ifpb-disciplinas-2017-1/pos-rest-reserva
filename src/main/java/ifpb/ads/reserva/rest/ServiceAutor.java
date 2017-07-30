package ifpb.ads.reserva.rest;

import ifpb.ads.reserva.domain.Autor;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.WebApplicationException;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/07/2017, 07:54:15
 */
@Stateless
public class ServiceAutor {

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Autor autorComId(int id) {
        Autor autor = em.find(Autor.class, id);
        if (autor == null) {
            throw new AutorException(String.format("Autor não encontrado com id: %s",id));
        }

        return autor;
    }

    public List<Autor> todosOsAutores() {
        return em.createQuery("FROM Autor a", Autor.class).getResultList();
    }
    
    public void salvar(Autor autor){
        if(autor==null){
            throw new AutorException(String.format("Autor não encontrado com id: %s"));
        }
        em.persist(autor);
    }
}
