package ifpb.ads.reserva.rest.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/08/2017, 10:34:04
 */
//@Stateless
@Named
@RequestScoped
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public boolean isUserValid(String user, String pass) {
        TypedQuery<User> createQuery = em.createQuery("SELECT u FROM  User u WHERE u.email=:email AND u.senha=:senha", User.class);
        createQuery.setParameter("email", user);
        createQuery.setParameter("senha", pass);
        return !createQuery.getResultList().isEmpty();
    }
}
