package br.com.optimate.manager.resource.port;

import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.PortFacilityService;
import br.com.optimate.manager.dto.PortFacilityDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/portFacility")
@RolesAllowed("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PortFacilityResource extends AbstractResource<PortFacilityService> {

    @POST
    @Path("/save")
    public Response savePortFacility(PortFacilityDto portFacilityDto) {
        return Response.ok(service.savePortFacility(portFacilityDto)).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/listAll")
    @RolesAllowed("user")
    public Response listAll() {
        return Response.ok(service.listAll()).status(Response.Status.FOUND).build();
    }

    @GET
    @Path("/findPortFacilityByName")
    public Response findPortFacilityByName(PortFacilityDto portFacilityDto) {
        return Response.ok(service.findPortFacilityByName(portFacilityDto)).status(Response.Status.FOUND).build();
    }

    @POST
    @Path("/editPortFacility")
    public Response editPortFacility(PortFacilityDto portFacilityDto) {
        return Response.ok(service.editPortFacility(portFacilityDto)).status(Response.Status.FOUND).build();
    }
}
