package br.com.optimate.manager.resource.user;

import br.com.optimate.manager.dto.UserDto;
import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/api/user")
@RolesAllowed("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource<UserService> {

    @POST
    @Path("/save")
    public Response saveUser(UserDto userDto) {
        return Response.ok(service.saveUser(userDto)).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/listAll")
    @RolesAllowed("user")
    public Response listAll() {
        return Response.ok(service.listAll()).build();
    }

    @GET
    @Path("/findUserByUsername")
    public Response findUserByUsername(UserDto userDto) {
        return Response.ok(service.findUserByUsername(userDto)).build();
    }

    @POST
    @Path("/editUser")
    public Response editUser(UserDto userDto) {
        return Response.ok(service.editUser(userDto)).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/currentUser")
    @RolesAllowed("user")
    public Response getCurrentUser() {
        return Response.ok(service.getCurrentUser()).build();
    }

    @DELETE
    @Path("/deleteUser")
    public Response deleteUser(@PathParam("id") long id) {
        return Response.ok(service.deleteUser(id)).build();
    }
}
