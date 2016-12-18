package northwind.rest.app.service;

import northwind.rest.app.dao.EmployeeDao;
import northwind.rest.app.model.Employee;

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
 * Rest service for displaying employees data.
 *
 * url: /rest/employee/*
 */
@Path("/employee")
public class EmployeeService extends BaseService<Employee> {
    public EmployeeService() {
        dao = new EmployeeDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAll() {
        return getAllObjects();
    }

    @GET
    @Path("/by/{attr}/{val}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getByAttribute(@PathParam("attr")String attribute, @PathParam("val")String value) {
        return getObjectsByAttribute(attribute, value);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getOne(@PathParam("id")Integer id) {
        return getSingleObject(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNew( Employee em ) throws URISyntaxException {
        return super.createNew(em);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Employee em) throws URISyntaxException {
        return super.updateObject(em);
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drop(@PathParam("id")Integer id) throws URISyntaxException {
        return super.dropObject(id);
    }
}
