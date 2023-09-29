package br.com.optimate.manager.resource.company;

import br.com.optimate.manager.resource.AbstractResource;
import br.com.optimate.manager.service.CompanyService;
import br.com.optimate.manager.dto.CompanyDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/company/create")
@RolesAllowed("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyCreateResource extends AbstractResource<CompanyService> {

    @POST
    @Path("/save")
    public Response saveCompany(CompanyDto companyDTO) {
        return Response.ok(service.saveCompany(companyDTO)).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/edit")
    public Response editCompany(CompanyDto companyDTO) {
        return Response.ok(service.editCompany(companyDTO)).build();
    }

//    @POST
//    @Path("/addBusinessArea")
//    public Response addBussinessArea(CompanyDto companyDTO) {
//        return Response.ok(service.addBusinessArea(companyDTO.getCompany().getBusinessAreaList())).build();
//    }
}
