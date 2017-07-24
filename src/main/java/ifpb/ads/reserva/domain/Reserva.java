package ifpb.ads.reserva.domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 18/07/2017, 14:51:29
 */
@Entity
public class Reserva implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String cliente;

    @Temporal(TemporalType.DATE)
    private LocalDate dataDaReserva;// = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private Status status = Status.CRIADA;
    @JoinTable
    @OneToMany
    private List<Livro> livros = new ArrayList<>();

    public Reserva() {
    }

    public Reserva(String cliente) {
        this.cliente = cliente;
    }

    public static Reserva of(String cliente) {
        return new Reserva(cliente);
    }

    public void agendar() {
        this.status = Status.AGENDADA;
        this.dataDaReserva = LocalDate.now();
    }

    public void concluir() {
        this.status = Status.ATENDIDA;
    }

    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void removerLivro(Livro livro) {
        this.livros.remove(livro);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataDaReserva() {
        return dataDaReserva;
    }

    public void setDataDaReserva(LocalDate dataDaReserva) {
        this.dataDaReserva = dataDaReserva;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

}
