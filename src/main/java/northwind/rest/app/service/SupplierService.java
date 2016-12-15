package northwind.rest.app.service;

import northwind.rest.app.dao.SupplierDao;
import northwind.rest.app.model.Supplier;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Rest service for displaying suppliers data.
 *
 * url: /rest/supplier/*
 */
@Path("/supplier")
public class SupplierService extends BaseService {
    public SupplierService() {
        dao = new SupplierDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Supplier> getAll() {
        return super.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Supplier getOne(@PathParam("id")Integer id) {
        return super.getOne(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNew( Supplier obj ) throws URISyntaxException {
        return super.createNew(obj);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSupplier(Supplier s) throws URISyntaxException {
        return super.updateObject(s);
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropSupplier(@PathParam("id")Integer id) throws URISyntaxException {
        return super.dropObject(id);
    }

    protected Response validateNewObject(Supplier s) {
        Response r = null;

        if(s.getCompanyName() == null) {
            r = Response.status(400).entity("Please provide the supplier company name.").build();
        }

        return r;
    }
}
