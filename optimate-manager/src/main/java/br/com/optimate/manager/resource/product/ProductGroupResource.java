package br.com.optimate.manager.resource.product;

import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.ProductGroupService;
import br.com.optimate.manager.dto.ProductGroupDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/productGroup")
@RolesAllowed("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductGroupResource extends AbstractResource<ProductGroupService> {

    @POST
    @Path("/save")
    public Response saveProductGroup(ProductGroupDto productGroupDto) {
        return Response.ok(service.saveProductGroup(productGroupDto)).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/listAll")
    @RolesAllowed({"admin", "user"})
    public Response listAll() {
        return Response.ok(service.listAll()).build();
    }

    @GET
    @Path("/findProductByAcronym")
    public Response findProductGroupByAcronym(ProductGroupDto productGroupDto) {
        return Response.ok(service.findListProductByAcronym(productGroupDto)).build();
    }

    @POST
    @Path("/editProductGroup")
    public Response editProductGroup(ProductGroupDto productGroupDto) {
        return Response.ok(service.editProductGroup(productGroupDto)).build();
    }

}
