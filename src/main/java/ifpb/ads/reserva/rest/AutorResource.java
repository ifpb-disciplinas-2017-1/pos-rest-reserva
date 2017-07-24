package ifpb.ads.reserva.rest;

import ifpb.ads.reserva.domain.Autor;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/07/2017, 10:41:04
 */
@Path("autor")
@Stateless
public class AutorResource {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response autores() {
        List<Autor> autores = em.createQuery("FROM Autor a", Autor.class).getResultList();
        GenericEntity<List<Autor>> entity = new GenericEntity<List<Autor>>(autores) {
        };

        return Response
                .ok()
                .entity(entity)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response novoAutor(
            Autor autor,
            @Context UriInfo uriInfo) {

        //Não façam isso em casa!
        em.persist(autor);
        String id = String.valueOf(autor.getId());
        URI location = uriInfo.getBaseUriBuilder() // ../api
                .path(AutorResource.class) // ../api/autor
                .path(id) // ../api/autor/id
                .build();
        return Response
                .created(location)
                .entity(autor)
                .build();
    }

//return Response
// .status(Response.Status.BAD_REQUEST)
// .entity(“Invalid address provided.”)
// .type(MediaType.APPLICATION_JSON)
// .build();
//    @GET
//    @Path("filtro/{primeiraLetra}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response autorIniciaCom(
//            @PathParam("primeiraLetra") 
//                    String primeiraLetra) {
//
//        Autor autor = autores.stream()
//                .filter(au -> au.getNome().toUpperCase()
//                .startsWith(primeiraLetra.toUpperCase()))
//                .findFirst().orElse(Autor.vazio());
//
//        return Response
//                .ok()
//                .entity(autor)
//                .build();
//    }
//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response autorIniciaCom(
//            @DefaultValue("c")
//            @QueryParam("primeiraLetra") 
//                    String primeiraLetra) {
//
//        Autor autor = autores.stream()
//                .filter(au -> au.getNome().toUpperCase()
//                .startsWith(primeiraLetra.toUpperCase()))
//                .findFirst().orElse(Autor.vazio());
//
//        return Response
//                .ok()
//                .entity(autor)
//                .build();
//    }
//    public Response autorComId(int id){
//        
//    }
//    http://localhost:8080/reservas/api/autor/c
//    private List<Autor> autores = Arrays.asList(
//            Autor.with("job@org.com", "Ricardo Job", "J.R"),
//            Autor.with("kiko@org.com", "Kiko 12", "K.K"),
//            Autor.with("chaves@org.com", "Chaves 8", "C.H."));
}
