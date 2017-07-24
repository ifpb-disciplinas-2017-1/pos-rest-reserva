package ifpb.ads.reserva.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 18/07/2017, 14:51:34
 */
@Entity
@XmlRootElement
public class Livro implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String edicao;
    private String titulo;
    private String descricao;
    @JoinTable
    @OneToMany
    private List<Autor> autores = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public void adicionarAutor(Autor autor) {
        this.autores.add(autor);
    }

    public void removerAutor(Autor autor) {
        this.autores.remove(autor);
    }
}
