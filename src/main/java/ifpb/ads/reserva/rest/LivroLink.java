package ifpb.ads.reserva.rest;

import ifpb.ads.reserva.domain.Autor;
import ifpb.ads.reserva.domain.Livro;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/07/2017, 08:42:43
 */
public class LivroLink implements Serializable {

    private String edicao;
    private String titulo;
    private String descricao;

    private List<Link> autores;

    public LivroLink() {
        this.autores = new ArrayList<>();
    }

    private LivroLink(String edicao, String titulo, String descricao) {
        this();
        this.edicao = edicao;
        this.titulo = titulo;
        this.descricao = descricao;

    }

    public static LivroLink of(final Livro livro, final UriInfo uriInfo) {
        LivroLink livroLink = new LivroLink(livro.getEdicao(), livro.getTitulo(), livro.getDescricao());
        List<Link> collect = livro.getAutores()
                .stream()
                .map((a) -> livroLink.toLink(a, uriInfo))
                .collect(Collectors.toList());
        livroLink.setAutores(collect);
        return livroLink;
    }

    private Link toLink(Autor t, UriInfo uriInfo) {
        String id = String.valueOf(t.getId());
        URI location = uriInfo.getBaseUriBuilder() // ../api
                .path(AutorResource.class) // ../api/autor
                .path(id) // ../api/autor/id
                .build();
        return new Link(location.toString(), "autor");
    }

    public void addAutorCom(Link link) {
        this.autores.add(link);
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Link> getAutores() {
        return autores;
    }

    public void setAutores(List<Link> autores) {
        this.autores = autores;
    }

}
