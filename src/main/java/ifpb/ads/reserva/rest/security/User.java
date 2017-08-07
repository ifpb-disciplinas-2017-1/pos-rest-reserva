package ifpb.ads.reserva.rest.security;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/08/2017, 10:32:45
 */
@Entity
@Table(name = "TbUser")
public class User implements Serializable{


    @Id
    private String email;
    private String senha;

    public User() {
    }

    private User(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    
    public static User of(String email, String senha) {
        return new User(email, senha);
    }
    
}
