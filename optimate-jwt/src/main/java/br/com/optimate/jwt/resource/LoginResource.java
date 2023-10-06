package br.com.optimate.jwt.resource;

import br.com.optimate.jwt.service.LoginService;
import br.com.optimate.jwt.dto.LoginDto;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class LoginResource extends AbstractResource<LoginService> {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/signin")
    public Response authenticateUser(LoginDto loginDto){
        return Response.ok(service.authenticate(loginDto)).build();
    }

    @POST
    @Path("/signout")
    public Response logoutUser() {
        return Response.ok().build();
    }

}
