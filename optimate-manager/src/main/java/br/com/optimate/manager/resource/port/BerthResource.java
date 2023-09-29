package br.com.optimate.manager.resource.port;

import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.BerthService;
import br.com.optimate.manager.dto.BerthDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/berth")
@RolesAllowed("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BerthResource extends AbstractResource<BerthService> {

    @POST
    @Path("/save")
    public Response saveBerth(BerthDto berthDto) {
        return Response.ok(service.saveBerth(berthDto)).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/listAll")
    @RolesAllowed("user")
    public Response listAll() {
        return Response.ok(service.listAll()).build();
    }

    @GET
    @Path("/findBerthByName")
    public Response findBerthByName(BerthDto berthDto) {
        return Response.ok(service.findBerthByName(berthDto)).build();
    }

    @POST
    @Path("/editBerth")
    public Response editBerth(BerthDto berthDto) {
        return Response.ok(service.editBerth(berthDto)).status(Response.Status.CREATED).build();
    }
}
