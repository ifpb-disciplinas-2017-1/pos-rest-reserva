package ifpb.ads.reserva.rest;

import ifpb.ads.reserva.domain.Autor;
import ifpb.ads.reserva.domain.Livro;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
@Path("livro")
@Stateless
public class LivroResource {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response autores() {
        List<Livro> livros = em.createQuery("FROM Livro l", Livro.class).getResultList();
        GenericEntity<List<Livro>> entity = new GenericEntity<List<Livro>>(livros) {
        };

        return Response
                .ok()
                .entity(entity)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response novoLivro(
            Livro livro,
            @Context UriInfo uriInfo) {

        //Não façam isso em casa!
        em.persist(livro);
        String id = String.valueOf(livro.getId());
        URI location = uriInfo.getBaseUriBuilder() // ../api
                .path(LivroResource.class) // ../api/livro
                .path(id) // ../api/livro/id
                .build();
        return Response
                .created(location)
                .entity(livro)
                .build();

    }

    // .../api/livro/{idLivro}/autor/{idAutor}
    @PUT
    @Path("{idLivro}/autor/{idAutor}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAutor(
            @PathParam("idLivro") int idLivro, @PathParam("idAutor") int idAutor) {

        Livro livro = em.find(Livro.class, idLivro);
        Autor autor = em.find(Autor.class, idAutor);
        livro.adicionarAutor(autor);
        return Response.ok(livro).build();
    }
}
