package northwind.rest.app.service;

import northwind.rest.app.dao.CategoryDao;
import northwind.rest.app.model.Category;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Rest service for displaying categories data.
 *
 * url: /rest/category/*
 */
@Path("/category")
public class CategoryService extends BaseService {
    public CategoryService() {
        dao = new CategoryDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAll() {
        return super.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getOne(@PathParam("id")Integer id) {
        return super.getOne(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newCategory( Category c ) throws URISyntaxException {
        if(c == null){
            return Response.status(400).entity("Please add category details.").build();
        }

        if(c.getName() == null) {
            return Response.status(400).entity("Please provide the category name.").build();
        }

        dao.openSession();
        dao.save(c);
        dao.closeSession();

        return Response.created(new URI("/rest/category/"+c.getId())).build();
    }
}
