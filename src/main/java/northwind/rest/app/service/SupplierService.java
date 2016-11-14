package northwind.rest.app.service;

import northwind.rest.app.dao.SupplierDao;
import northwind.rest.app.model.Supplier;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest service for displaying suppliers data.
 *
 * url: /rest/supplier/*
 */
@Path("/supplier")
public class SupplierService {

    private SupplierDao supplierDao = new SupplierDao();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Supplier> getAll() {

        return supplierDao.getAll();
    }
}
