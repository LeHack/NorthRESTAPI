package northwind.rest.app.service;

import northwind.rest.app.dao.CustomerDao;
import northwind.rest.app.dao.EmployeeDao;
import northwind.rest.app.dao.OrderDao;
import northwind.rest.app.dao.ShipperDao;
import northwind.rest.app.model.Customer;
import northwind.rest.app.model.Employee;
import northwind.rest.app.model.Order;
import northwind.rest.app.model.Shipper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;

import java.util.List;

/**
 * Rest service for displaying orders data.
 *
 * url: /rest/order/*
 */
@Path("/order")
public class OrderService extends BaseService {
    public OrderService() {
        dao = new OrderDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAll() {
        return super.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOne(@PathParam("id")Integer id) {
        return super.getOne(id);
    }

    @GET
    @Path("/customer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getByCustomer(@PathParam("id")String id) {
        List<Order> orders;

        Session s = dao.openSession();
        CustomerDao cDao = new CustomerDao(s);
        Customer c = cDao.getById(id);
        orders = ((OrderDao)dao).getByCustomer(c);
        dao.closeSession();

        return orders;
    }

    @GET
    @Path("/employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getByEmployee(@PathParam("id")Integer id) {
        List<Order> orders;

        Session s = dao.openSession();
        EmployeeDao eDao = new EmployeeDao(s);
        Employee e = eDao.getById(id);
        orders = ((OrderDao)dao).getByEmployee(e);
        dao.closeSession();

        return orders;
    }

    @GET
    @Path("/shipper/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getByShipper(@PathParam("id")Integer id) {
        List<Order> orders;

        Session sess = dao.openSession();
        ShipperDao sDao = new ShipperDao(sess);
        Shipper s = sDao.getById(id);
        orders = ((OrderDao)dao).getByShipper(s);
        dao.closeSession();

        return orders;
    }

}
