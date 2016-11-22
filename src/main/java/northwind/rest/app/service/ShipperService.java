package northwind.rest.app.service;

import northwind.rest.app.dao.ShipperDao;
import northwind.rest.app.model.Shipper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest service for displaying shippers data.
 *
 * url: /rest/shipper/*
 */
@Path("/shipper")
public class ShipperService {

    private ShipperDao shipperDao = new ShipperDao();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shipper> getAll() {

        return shipperDao.getAll();
    }
}
