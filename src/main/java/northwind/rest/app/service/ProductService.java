package northwind.rest.app.service;

import northwind.rest.app.dao.OrderDetailsDao;
import northwind.rest.app.dao.ProductDao;
import northwind.rest.app.model.OrderDetails;
import northwind.rest.app.model.Product;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Transaction;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Rest service for displaying products data.
 *
 * url: /rest/product/*
 */
@Path("/product")
public class ProductService extends BaseService<Product> {
    public ProductService() {
        dao = new ProductDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll() {
        return getAllObjects();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getOne(@PathParam("id")Integer id) {
        return getSingleObject(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNew( Product p ) throws URISyntaxException {
        return super.createNew(p);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update( Product p ) throws URISyntaxException {
        return super.updateObject(p);
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drop(@PathParam("id")Integer id) throws URISyntaxException {
        Response r = Response.status(200).entity("Object removed OK.\n").build();
        Transaction t = dao.openSession().beginTransaction();
        Product obj = dao.getById(id);
        if (obj != null) {
            Integer productId = obj.getId();
            // also make sure to remove OrderDetails for this order
            OrderDetailsDao odDao = new OrderDetailsDao(dao.getSession());
            for (OrderDetails od : odDao.getByProductId(productId)) {
                odDao.delete(od);
            }
            // remove the order
            dao.delete(obj);
            t.commit();
        }
        else {
            t.rollback();
            r = Response.status(400).entity("The object does not exist in DB.\n").build();
        }
        dao.closeSession();
        return r;
    }
}
