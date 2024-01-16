package br.com.optimate.jwt.resource;

import br.com.optimate.jwt.config.MessageResponse;
import br.com.optimate.jwt.domain.User;
import br.com.optimate.jwt.dto.LoginDto;
import br.com.optimate.jwt.service.LoginService;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@Path("/api")
public class LoginResource extends AbstractResource<LoginService> {

    @RestClient
    UserResource userResource;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/signin")
    public Response authenticateUser(LoginDto loginDto){
        Optional<User> optionalUser = Optional.ofNullable(this.userResource.getByUsername(loginDto.getUsername()));
        User userFound = optionalUser.orElseThrow(() ->
                new WebApplicationException(Response.ok(new MessageResponse("Usuário não econtrado!")).status(Response.Status.NOT_FOUND).build()));
        return Response.ok(service.authenticate(loginDto, userFound)).build();
    }

    @POST
    @Path("/signout")
    public Response logoutUser() {
        return Response.ok().build();
    }

}
