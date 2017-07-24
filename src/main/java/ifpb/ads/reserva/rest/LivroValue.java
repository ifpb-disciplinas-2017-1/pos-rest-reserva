package ifpb.ads.reserva.rest;

import ifpb.ads.reserva.domain.Livro;
import java.io.Serializable;
import javax.ws.rs.FormParam;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/07/2017, 12:02:25
 */
public class LivroValue implements Serializable {

    @FormParam("edicao")
    private String edicao;
    @FormParam("titulo")
    private String titulo;
    @FormParam("descricao")
    private String descricao;

    public LivroValue() {
    }

    public LivroValue(String edicao, String titulo, String descricao) {
        this.edicao = edicao;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Livro toLivro() {
        return new Livro(edicao, titulo, descricao);
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

}
