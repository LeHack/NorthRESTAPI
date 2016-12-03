package northwind.rest.app.service;

import northwind.rest.app.dao.SupplierDao;
import northwind.rest.app.model.Supplier;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
}
