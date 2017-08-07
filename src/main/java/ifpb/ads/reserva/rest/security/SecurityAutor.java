package ifpb.ads.reserva.rest.security;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;
import javax.enterprise.inject.spi.CDI;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/08/2017, 09:56:39
 */
@Provider
public class SecurityAutor implements ContainerRequestFilter {

    private final UserService service;

    public SecurityAutor() {
        service = CDI.current().select(UserService.class).get();
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (!pathSecurity(requestContext)) {
            return;
        }
        //Basic am9iOjEyMw==
        String headerString = requestContext.getHeaderString("Authorization"); 
        if (!headerIsValid(headerString)) {
            headerNotValid(requestContext);
        }
        StringTokenizer st = decoderToken(headerString);
        if (!service.isUserValid(st.nextToken(), st.nextToken())) {
            userNotAuthorized(requestContext);
        }
        //usuario válido 

    }

    private StringTokenizer decoderToken(String headerString) {
        String token = headerString.replace("Basic ", ""); //am9iOjEyMw==
        String decode = new String(Base64.getDecoder().decode(token)); //job:123
        StringTokenizer st = new StringTokenizer(decode, ":");
        return st;
    }

    private void headerNotValid(ContainerRequestContext requestContext) {
        JsonObject add = Json.createObjectBuilder().add("msg", "header not valid").build();
        Response response = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(add)
                .build();
        requestContext.abortWith(response);
    }

    private void userNotAuthorized(ContainerRequestContext requestContext) {
        JsonObject add = Json.createObjectBuilder().add("msg", "user not authorized").build();
        Response response = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(add)
                .build();
        requestContext.abortWith(response);
    }

    private boolean pathSecurity(ContainerRequestContext requestContext) {
        return requestContext.getUriInfo().getPath().contains("autor");
    }

    private boolean headerIsValid(String headerString) {
        return headerString != null && !headerString.isEmpty();
    }

}
