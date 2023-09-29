package br.com.optimate.manager.resource.port;

import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.PortService;
import br.com.optimate.manager.dto.PortDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/port")
@RolesAllowed("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PortResource extends AbstractResource<PortService> {

    @POST
    @Path("/save")
    public Response saveport(PortDto portDto) {
        return Response.ok(service.savePort(portDto)).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/listAll")
    @RolesAllowed("user")
    public Response listaAll() {
        return Response.ok(service.listAll()).status(Response.Status.FOUND).build();
    }

    @GET
    @Path("/findPortByName")
    public Response findPortyName(PortDto portDto) {
        return Response.ok(service.findPortByName(portDto)).status(Response.Status.FOUND).build();
    }

    @POST
    @Path("/editPort")
    public Response editPort(PortDto portDto) {
        return Response.ok(service.editPort(portDto)).build();
    }
}
