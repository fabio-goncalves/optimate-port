package br.com.optimate.jwt.resource;

import br.com.optimate.jwt.domain.User;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/user")
@RegisterRestClient(configKey="users-api")
public interface UserResource {

    @GET
    @Path("/findUser")
    @Produces(MediaType.APPLICATION_JSON)
    User getByUsername(@QueryParam("username") String username);

}
