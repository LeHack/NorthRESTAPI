package northwind.rest.app.service;

import northwind.rest.app.dao.OrderDetailsDao;
import northwind.rest.app.model.OrderDetails;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest service for displaying order details data.
 *
 * url: /rest/order-details/*
 */
@Path("/order-details")
public class OrderDetailsService extends BaseService<OrderDetails> {
    public OrderDetailsService() {
        dao = new OrderDetailsDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDetails> getAll() {
        return getAllObjects();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetails getOne(@PathParam("id")Integer id) {
        return getSingleObject(id);
    }
}
