package ifpb.ads.reserva.rest;

import ifpb.ads.reserva.domain.Autor;
import ifpb.ads.reserva.domain.Livro;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/07/2017, 11:27:13
 */
@Path("/")
@Stateless
public class SubLivroResource {

    @PersistenceContext
    private EntityManager em;

    @PUT
    @Path("{idAutor}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAutor(
            @PathParam("idLivro") int idLivro, @PathParam("idAutor") int idAutor) {

        Livro livro = em.find(Livro.class, idLivro);
        Autor autor = em.find(Autor.class, idAutor);
        livro.adicionarAutor(autor);
        livro.getAutores();
        return Response.ok(livro).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAutor(@PathParam("idLivro") int idLivro) {

        Livro livro = em.find(Livro.class, idLivro);

        GenericEntity<List<Autor>> entity = new GenericEntity<List<Autor>>(livro.getAutores()) {
        };

        return Response
                .ok()
                .entity(entity)
                .build();
    }

    @GET
    @Path("{idAutor}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAutor(
            @PathParam("idLivro") int idLivro, @PathParam("idAutor") int idAutor) {

        Livro livro = em.find(Livro.class, idLivro);

        Optional<Autor> autor = livro.getAutores()
                .stream()
                .filter(a -> a.getId() == idAutor)
                .findFirst();
//                .orElse(null);

        if (!autor.isPresent()) {
            return Response.noContent().build();
        }
        return Response.ok(autor.get()).build();

    }
}
