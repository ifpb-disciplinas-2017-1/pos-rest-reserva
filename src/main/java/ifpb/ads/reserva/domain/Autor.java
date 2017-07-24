package ifpb.ads.reserva.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 18/07/2017, 14:51:22
 */
@Entity
@XmlRootElement
public class Autor implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String nome;
    private String abreviacao;

    public Autor() {
    }

    private Autor(String email, String nome, String abreviacao) {
        this.email = email;
        this.nome = nome;
        this.abreviacao = abreviacao;
    }

    public static Autor with(String email, String nome, String abreviacao) {
        return new Autor(email, nome, abreviacao);
    }

    public static Autor vazio() {
        return new Autor("", "", "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

}
