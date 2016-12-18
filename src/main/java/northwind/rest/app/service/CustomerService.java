package northwind.rest.app.service;

import northwind.rest.app.dao.CustomerDao;
import northwind.rest.app.model.Customer;

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
 * Rest service for displaying customers data.
 *
 * url: /rest/customer/*
 */
@Path("/customer")
public class CustomerService extends BaseService<Customer> {
    public CustomerService() {
        dao = new CustomerDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAll() {
        return getAllObjects();
    }

    @GET
    @Path("/by/{attr}/{val}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getByAttribute(@PathParam("attr")String attribute, @PathParam("val")String value) {
        return getObjectsByAttribute(attribute, value);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getOne(@PathParam("id")String id) {
        return getSingleObject(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNew( Customer cust ) throws URISyntaxException {
        return super.createNew(cust);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Customer cust) throws URISyntaxException {
        return super.updateObject(cust);
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drop(@PathParam("id")Integer id) throws URISyntaxException {
        return super.dropObject(id);
    }
}
