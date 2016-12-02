package northwind.rest.app.service;

import northwind.rest.app.dao.OrderDao;
import northwind.rest.app.model.Order;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest service for displaying orders data.
 *
 * url: /rest/order/*
 */
@Path("/order")
public class OrderService {

    private OrderDao orderDao = new OrderDao();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOne(@PathParam("id")Integer id) {
        return orderDao.getById(orderDao.getSession(), id);
    }

}
