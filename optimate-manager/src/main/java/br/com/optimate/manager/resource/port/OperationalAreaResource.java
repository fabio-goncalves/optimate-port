package br.com.optimate.manager.resource.port;

import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.OperationalAreaService;
import br.com.optimate.manager.dto.OperationalAreaDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/operationalArea")
@RolesAllowed("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperationalAreaResource extends AbstractResource<OperationalAreaService> {

    @POST
    @Path("/save")
    public Response saveOperationalArea(OperationalAreaDto operationalAreaDto) {
        return Response.ok(service.saveOperationalArea(operationalAreaDto)).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/listAll")
    @RolesAllowed("user")
    public Response listAll(){
        return Response.ok(service.listAll()).build();
    }

    @GET
    @Path("/findMooringLocationByAcronym")
    public Response findMooringLocationByAcronym(OperationalAreaDto operationalAreaDto) {
        return Response.ok(service.findMooringLocationByAcronym(operationalAreaDto)).status(Response.Status.OK).build();
    }

    @POST
    @Path("/edit")
    public Response editMooringLocation(OperationalAreaDto operationalAreaDto) {
        return Response.ok(service.editMooringLocation(operationalAreaDto)).build();
    }
}
