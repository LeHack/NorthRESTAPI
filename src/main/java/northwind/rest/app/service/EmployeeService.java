package northwind.rest.app.service;

import northwind.rest.app.dao.EmployeeDao;
import northwind.rest.app.model.Employee;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest service for displaying employees data.
 *
 * url: /rest/employee/*
 */
@Path("/employee")
public class EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDao();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAll() {

        return employeeDao.getAll();
    }
}
