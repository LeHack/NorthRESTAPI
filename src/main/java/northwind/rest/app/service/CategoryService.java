package northwind.rest.app.service;

import northwind.rest.app.dao.CategoryDao;
import northwind.rest.app.model.Category;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest service for displaying categories data.
 *
 * url: /rest/category/*
 */
@Path("/category")
public class CategoryService {

    private CategoryDao categoryDao = new CategoryDao();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAll() {

        return categoryDao.getAll();
    }
}
