package northwind.rest.app.service;

import northwind.rest.app.dao.CustomerDao;
import northwind.rest.app.model.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest service for displaying customers data.
 *
 * url: /rest/customer/*
 */
@Path("/customer")
public class CustomerService extends BaseService {
    public CustomerService() {
        dao = new CustomerDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAll() {
        return super.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getOne(@PathParam("id")String id) {
        return super.getOne(id);
    }
}
