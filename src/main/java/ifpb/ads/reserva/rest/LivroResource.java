package ifpb.ads.reserva.rest;

import ifpb.ads.reserva.domain.Livro;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
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
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class LivroResource {

    // ../api/reserva/1?status=agendada
    @PersistenceContext
    private EntityManager em;

    @Context
    private ResourceContext resourceContext;

    @GET
    @Path("{idLivro}")
    public Response livroComId(@PathParam("idLivro") int idLivro,
            @Context UriInfo uriInfo) {
        Livro livro = em.find(Livro.class, idLivro);
        LivroLink response = LivroLink.of(livro, uriInfo);
        return Response
                .ok()
                .entity(response)
                .build();
    }
    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response todosOsLivros() {
        List<Livro> livros = em.createQuery("FROM Livro l", Livro.class).getResultList();
        GenericEntity<List<Livro>> entity = new GenericEntity<List<Livro>>(livros) {
        };

        return Response
                .ok()
                .entity(entity)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response novoLivro(
            @BeanParam LivroValue livroValue,
            @Context UriInfo uriInfo) {
        Livro livro = livroValue.toLivro();
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
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response novoLivro(
//            @FormParam("edicao") String edicao,
//            @FormParam("titulo") String titulo,
//            @FormParam("descricao") String descricao,
//            @Context UriInfo uriInfo) {
//        Livro livro = new Livro(edicao, titulo, descricao);
//        em.persist(livro);
//        String id = String.valueOf(livro.getId());
//        URI location = uriInfo.getBaseUriBuilder() // ../api
//                .path(LivroResource.class) // ../api/livro
//                .path(id) // ../api/livro/id
//                .build();
//        return Response
//                .created(location)
//                .entity(livro)
//                .build();
//
//    }
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response novoLivro(
//            Livro livro,
//            @Context UriInfo uriInfo) {
//
//        //Não façam isso em casa!
//        em.persist(livro);
//        String id = String.valueOf(livro.getId());
//        URI location = uriInfo.getBaseUriBuilder() // ../api
//                .path(LivroResource.class) // ../api/livro
//                .path(id) // ../api/livro/id
//                .build();
//        return Response
//                .created(location)
//                .entity(livro)
//                .build();
//
//    }

    // .../api/livro/{idLivro}/autor/{idAutor}
//    @PUT
    @Path("{idLivro}/autor")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public SubLivroResource addAutor() {

//        Livro livro = em.find(Livro.class, idLivro);
//        Autor autor = em.find(Autor.class, idAutor);
//        livro.adicionarAutor(autor);
//        return Response.ok(livro).build();
        return resourceContext.getResource(SubLivroResource.class);
//        return new SubLivroResource();
    }

    //http://localhost:8080/reservas/api/livro/351/autor/151
    //http://localhost:8080/reservas/api/livro/351/autor/151
    //http://localhost:8080/reservas/api/livro/301/autor
//    @GET
//    @Path("{idLivro}/autor")
//    public SubLivroResource getAutor() {
//
//        return new SubLivroResource();
//    }
//    @GET
//    @Path("{idLivro}/autor/{idAutor}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getAutor(
//            @PathParam("idLivro") int idLivro, @PathParam("idAutor") int idAutor) {
//
//        Livro livro = em.find(Livro.class, idLivro);
//
//        Optional<Autor> autor = livro.getAutores()
//                .stream()
//                .filter(a -> a.getId() == idAutor)
//                .findFirst();
////                .orElse(null);
//
//        if (!autor.isPresent()) {
//            return Response.noContent().build();
//        }
//        return Response.ok(autor.get()).build();
//
//    }
}

//    @Context
//    private ResourceContext resourceContext;
// return this.resourceContext
//                .getResource(SubOrderResources.class);
