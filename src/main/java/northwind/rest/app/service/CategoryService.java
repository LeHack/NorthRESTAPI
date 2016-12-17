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

import java.net.URISyntaxException;
import java.util.List;

/**
 * Rest service for displaying categories data.
 *
 * url: /rest/category/*
 */
@Path("/category")
public class CategoryService extends BaseService<Category> {
    public CategoryService() {
        dao = new CategoryDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAll() {
        return getAllObjects();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getOne(@PathParam("id")Integer id) {
        return getSingleObject(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNew( Category cat ) throws URISyntaxException {
        return super.createNew(cat);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Category cat) throws URISyntaxException {
        return super.updateObject(cat);
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drop(@PathParam("id")Integer id) throws URISyntaxException {
        return super.dropObject(id);
    }
}
