package br.com.optimate.jwt.resource;

import br.com.optimate.jwt.service.LoginService;
import br.com.optimate.jwt.service.dto.LoginDto;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginResource extends AbstractResource<LoginService> {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/authenticate")
    public Response authenticate(LoginDto loginDto){
        return Response.ok(service.authenticate(loginDto)).build();
    }
}
