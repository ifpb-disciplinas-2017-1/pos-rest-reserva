package ifpb.ads.reserva.rest;

import java.io.Serializable;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/07/2017, 08:45:21
 */
public class Link implements Serializable {

    private String href;
    private String rel;
    
//    private String title;

    public Link(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }

// "href": "http://localhost:8080/reservas/api/autor/202"
//        "rel": "autor"
    public Link() {
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

}
