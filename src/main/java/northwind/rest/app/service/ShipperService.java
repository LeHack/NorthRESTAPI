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

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Rest service for displaying shippers data.
 *
 * url: /rest/shipper/*
 */
@Path("/shipper")
public class ShipperService extends BaseService {
    public ShipperService() {
        dao = new ShipperDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shipper> getAll() {
        return super.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shipper getOne(@PathParam("id")Integer id) {
        return super.getOne(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newShipper( Shipper s ) throws URISyntaxException {
        if(s == null){
            return Response.status(400).entity("Please add shipper details.").build();
        }

        if(s.getCompanyName() == null) {
            return Response.status(400).entity("Please provide the shipper company name.").build();
        }

        dao.openSession();
        dao.save(s);
        dao.closeSession();

        return Response.created(new URI("/rest/shipper/"+s.getId())).build();
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShipper(Shipper s ) throws URISyntaxException {
        if(s == null){
            return Response.status(400).entity("Please specify shipper details to update.").build();
        }

        Transaction t = dao.openSession().beginTransaction();
        Shipper fromDb = dao.getById(s.getId());
        fromDb.setCompanyName( s.getCompanyName() );
        dao.update(fromDb);
        t.commit();
        dao.closeSession();

        return Response.created(new URI("/rest/shipper/"+s.getId())).build();
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropShipper(@PathParam("id")Integer id) throws URISyntaxException {
        Transaction t = dao.openSession().beginTransaction();
        Shipper shipper = dao.getById(id);
        dao.delete(shipper);
        t.commit();
        dao.closeSession();
        return Response.status(200).entity("Shipper: " + id + " deleted from database.").build();
    }
}
