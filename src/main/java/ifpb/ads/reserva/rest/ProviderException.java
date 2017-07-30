package ifpb.ads.reserva.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/07/2017, 08:03:25
 */
@Provider
public class ProviderException implements ExceptionMapper<AutorException> {

    @Override
    public Response toResponse(AutorException exception) {
        ErroMessage erro = ErroMessage.of(404, "resource not found", exception.getMessage());
        return Response
                .status(Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(erro)
//                .entity("{\"erro\":\"aldfafa\"}")
                .build();

    }

}
