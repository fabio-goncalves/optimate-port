package br.com.optimate.manager.resource.port;

import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.MooringLocationService;
import br.com.optimate.manager.dto.MooringLocationDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/mooringLocation")
@RolesAllowed("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MooringLocationResource extends AbstractResource<MooringLocationService> {

    @POST
    @Path("/save")
    public Response saveMooringLocation(MooringLocationDto mooringLocationDto)  {
        return Response.ok(service.saveMooringLocation(mooringLocationDto)).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/listAll")
    @RolesAllowed("user")
    public Response listAll() {
        return Response.ok(service.listAll()).build();
    }

    @GET
    @Path("/findMooringLocationByAcronym")
    public Response findMooringLocationByAcronym(MooringLocationDto mooringLocationDto) {
        return Response.ok(service.findMooringLocationByAcronym(mooringLocationDto)).build();
    }

    @POST
    @Path("/editMooringLocation")
    public Response editMooringLocation(MooringLocationDto mooringLocationDto)  {
        return Response.ok(service.editMooringLocation(mooringLocationDto)).build();
    }
}
