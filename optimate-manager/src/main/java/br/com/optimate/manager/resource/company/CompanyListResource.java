package br.com.optimate.manager.resource.company;

import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.CompanyService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/company/list")
@RolesAllowed("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyListResource extends AbstractResource<CompanyService> {

    @GET
    @Path("/findCompanyByName")
    public Response findCompanyByName(@QueryParam("name") String name) {
        return Response.ok(service.findCompanyByName(name)).build();
    }

    @GET
    @Path("/findCompanyByStatus")
    public Response findCompanyByStatus(@QueryParam("isActive") Boolean isActive) {
        return Response.ok(service.findCompanyByStatus(isActive)).build();
    }

    @GET
    @Path("/findCompanyByCNPJ")
    public Response findCompanyByCNPJ(@QueryParam("cnpj") String cnpj) {
        return Response.ok(service.findCompanyByCNPJ(cnpj)).build();
    }

    @GET
    @Path("/findListCompanyByCNPJ")
    public Response findListCompanyByName(@QueryParam("name") String name) {
        return Response.ok(service.findListCompanyByName(name)).build();
    }

    @GET
    @Path("/listAll")
    public Response listAllCompanies() {
        return Response.ok(service.listAll()).build();
    }

}
