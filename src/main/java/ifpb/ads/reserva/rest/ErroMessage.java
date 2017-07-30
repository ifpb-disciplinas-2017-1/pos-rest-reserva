package ifpb.ads.reserva.rest;

import java.io.Serializable;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/07/2017, 08:10:07
 */
public class ErroMessage implements Serializable {

    private int code;
    private String message;
    private String description;

    public ErroMessage() {
    }

    private ErroMessage(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public static ErroMessage of(int code, String message, String description) {
        return new ErroMessage(code, message, description);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
