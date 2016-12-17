package northwind.rest.app.service;

import northwind.rest.app.dao.ShipperDao;
import northwind.rest.app.model.Shipper;

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
 * Rest service for displaying shippers data.
 *
 * url: /rest/shipper/*
 */
@Path("/shipper")
public class ShipperService extends BaseService<Shipper> {
    public ShipperService() {
        dao = new ShipperDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shipper> getAll() {
        return getAllObjects();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shipper getOne(@PathParam("id")Integer id) {
        return getSingleObject(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNew( Shipper obj ) throws URISyntaxException {
        return super.createNew(obj);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShipper(Shipper s) throws URISyntaxException {
        return super.updateObject(s);
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropShipper(@PathParam("id")Integer id) throws URISyntaxException {
        return super.dropObject(id);
    }

    protected Response validateNewObject(Shipper s) {
        Response r = null;

        if(s.getCompanyName() == null) {
            r = Response.status(400).entity("Please provide the shipper company name.").build();
        }

        return r;
    }
}
