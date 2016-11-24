package northwind.rest.app.service;

import northwind.rest.app.dao.ProductDao;
import northwind.rest.app.model.OrderDetails;
import northwind.rest.app.model.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest service for displaying products data.
 *
 * url: /rest/product/*
 */
@Path("/product")
public class ProductService {

    private ProductDao productDao = new ProductDao();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll() {

        return productDao.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getOne(@PathParam("id")Integer id) {

        return productDao.getById(productDao.getSession(), id);
    }
}
