package ifpb.ads.reserva.domain;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 18/07/2017, 15:07:16
 */
public class Main {

    public static void main(String[] args) {
        Main.persist(Reserva.of("Job"));
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ifpb.ads_mavenproject1-a_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    //@Context UriInfo info 
    //new GenericEntity<List<Produto>>(resultList) {}
//@HeaderParam("Referer") String referer
// @CookieParam("customerId") Cookie custId
// Cookie cookie = new Cookie("key", "value");
// @BeanParam Produto produto, no Bean: @FormParam("nome")
//Form form = new Form().param("nome", "Frango")
//                      .param("valor", "3.7);
//    @GET
//    @Path("{produtoId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_XML)
//    public Response listarPorId(@PathParam("produtoId") int id)
//    public Response filtrarPorQuery(@QueryParam("ordem") String ordem) // @DefaultValue("id")
//    public Response salvar(@FormParam("nome") String nome, @FormParam("valor") double valor) 
//public Response atualizar(@BeanParam Produto produto) 
//    public Response listarPorNome(@MatrixParam("nome") String nome) 

}
