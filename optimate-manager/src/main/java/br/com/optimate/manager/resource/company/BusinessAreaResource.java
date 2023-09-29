package br.com.optimate.manager.resource.company;

import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.BusinessAreaService;
import br.com.optimate.manager.dto.BusinessAreaDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/business")
@RolesAllowed("admin")
public class BusinessAreaResource extends AbstractResource<BusinessAreaService> {

    @POST
    @Path("/save")
    public Response saveBusinessArea(BusinessAreaDto businessAreaDTO) {
        return Response.ok(service.saveBusinessArea(businessAreaDTO)).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/list")
    @RolesAllowed("user")
    public Response listBusinessArea() {
        return Response.ok(service.listBusinessArea()).build();
    }

    @POST
    @Path("/edit")
    public Response editBusinessArea(BusinessAreaDto businessAreaDTO) {
        return Response.ok(service.editBusinessArea(businessAreaDTO)).build();
    }
}
