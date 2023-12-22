package br.com.optimate.manager.resource.product;

import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.ProductService;
import br.com.optimate.manager.dto.ProductDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/product")
@RolesAllowed("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource extends AbstractResource<ProductService> {

    @POST
    @Path("/save")
    public Response saveProduct(ProductDto productDto) {
        return Response.ok(service.saveProduct(productDto)).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/listAll")
    @RolesAllowed({"admin", "user"})
    public Response listAll() {
        return Response.ok(service.listAll()).build();
    }

    @GET
    @Path("/findProductByAcronym")
    public Response findProductByAcronym(ProductDto productDto) {
        return Response.ok(service.findProductByAcronym(productDto)).build();
    }

    @POST
    @Path("/editProduct")
    public Response editProduct(ProductDto productDto) {
        return Response.ok(service.editProduct(productDto)).build();
    }
}
