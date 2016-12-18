package northwind.rest.app.service;

import northwind.rest.app.dao.BaseDao;
import northwind.rest.app.dao.CustomerDao;
import northwind.rest.app.dao.EmployeeDao;
import northwind.rest.app.dao.OrderDao;
import northwind.rest.app.dao.OrderDetailsDao;
import northwind.rest.app.dao.ProductDao;
import northwind.rest.app.dao.ShipperDao;
import northwind.rest.app.model.Customer;
import northwind.rest.app.model.Employee;
import northwind.rest.app.model.Order;
import northwind.rest.app.model.OrderDetails;
import northwind.rest.app.model.OrderDetailsPK;
import northwind.rest.app.model.Product;
import northwind.rest.app.model.Shipper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest service for displaying orders data.
 *
 * url: /rest/order/*
 */
@Path("/order")
public class OrderService extends BaseService<Order> {
    public OrderService() {
        dao = new OrderDao();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAll() {
        return getAllObjects();
    }

    @GET
    @Path("/by/{attr}/{val}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getByAttribute(@PathParam("attr")String attribute, @PathParam("val")String value) {
        return getObjectsByAttribute(attribute, value);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOne(@PathParam("id")Integer id) {
        return getSingleObject(id);
    }

    @GET
    @Path("/customer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getByCustomer(@PathParam("id")String id) {
        List<Order> orders;

        Session s = dao.openSession();
        CustomerDao cDao = new CustomerDao(s);
        Customer c = cDao.getById(id);
        orders = ((OrderDao)dao).getByCustomer(c);
        dao.closeSession();

        return orders;
    }

    @GET
    @Path("/employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getByEmployee(@PathParam("id")Integer id) {
        List<Order> orders;

        Session s = dao.openSession();
        EmployeeDao eDao = new EmployeeDao(s);
        Employee e = eDao.getById(id);
        orders = ((OrderDao)dao).getByEmployee(e);
        dao.closeSession();

        return orders;
    }

    @GET
    @Path("/shipper/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getByShipper(@PathParam("id")Integer id) {
        List<Order> orders;

        Session sess = dao.openSession();
        ShipperDao sDao = new ShipperDao(sess);
        Shipper s = sDao.getById(id);
        orders = ((OrderDao)dao).getByShipper(s);
        dao.closeSession();

        return orders;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNew( String json ) throws URISyntaxException {
        Response r;
        // convert JSON string to Map
        Session session = dao.openSession();
        Transaction t = session.beginTransaction();
        try {
            // we need a mutable map
            HashMap<String, Object> map = (new ObjectMapper()).readValue(json, new TypeReference<HashMap<String, Object>>(){});

            // extract data which needs additional parsing
            Object orders = map.remove("order");
            List<Map<String, Object>> orderDetails = new ArrayList<>();
            // check it step by step
            if (orders instanceof List) {
                List<?> tmpDetails = (List<?>)orders;
                for (Object entry : tmpDetails) {
                    Map<?,?> tmpEntry = (Map<?,?>)entry;
                    Map<String, Object> tmpMap = new HashMap<>();
                    for (Object key : tmpEntry.keySet()) {
                        if (key instanceof String) {
                            tmpMap.put((String)key, tmpEntry.get(key));
                        }
                    }
                    if (!tmpMap.isEmpty()) {
                        orderDetails.add( tmpMap );
                    }
                }
            }
            if (orderDetails.isEmpty()) {
                throw new IOException("Incorrect format of orders data.");
            }

            Customer cust     = getObject(new CustomerDao(session), map, "customerId");
            Employee empl     = getObject(new EmployeeDao(session), map, "employeeId");
            Shipper ship      = getObject(new ShipperDao(session),  map, "shipperId");
            Date orderDate    = asDate((String)map.remove("orderDate"));
            Date requiredDate = asDate((String)map.remove("requiredDate"));
            Date shippedDate  = asDate((String)map.remove("shippedDate"));

            // now create a new order object and set it up
            Order order = new Order();
            // set external objects
            order.setCustomer(cust);
            order.setEmployee(empl);
            order.setShipVia(ship);
            // set parsed dates
            order.setOrderDate(orderDate);
            order.setRequiredDate(requiredDate);
            order.setShippedDate(shippedDate);
            // everything else
            order.setFromMap(map);
            dao.save(order);

            // now use the map to construct the OrderDetails entries
            OrderDetailsDao odDao = new OrderDetailsDao(session);
            ProductDao pDao = new ProductDao(session);
            for (Map<String, Object> orderRow : orderDetails) {
                Product prod = getObject(pDao, orderRow, "productId");
                if (prod.getDiscontinued().equals(1)) {
                    throw new IOException("Cannot order a discontinued product");
                }

                OrderDetails od = new OrderDetails();
                // check required data
                for (String reqKey : Arrays.asList("quantity", "unitPrice")) {
                    if (!orderRow.containsKey(reqKey)) {
                        throw new IOException(reqKey + " missing in order item");
                    }
                }
                // make sure Float fields contain the proper type, also set default 0.0f for discount if not set
                ensureFloat(orderRow, Arrays.asList("unitPrice", "discount"));
                // set it
                od.setFromMap(orderRow);

                OrderDetailsPK odId = new OrderDetailsPK();
                odId.setOrder(order);
                odId.setProduct(prod);
                od.setId(odId);

                odDao.save(od);
            }
            // finally commit
            t.commit();
            r = Response.status(200).entity("Order "+ order.getId() +" created correctly.\n").build();
        } catch (IOException e) {
            r = Response.status(500).entity("Cannot parse received JSON string due to: " + e.getMessage() + "\n").build();
            t.rollback();
        }
        dao.closeSession();

        return r;
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Order o) throws URISyntaxException {
        return super.updateObject(o);
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drop(@PathParam("id")Integer id) throws URISyntaxException {
        Response r = Response.status(200).entity("Object removed OK.\n").build();
        Transaction t = dao.openSession().beginTransaction();
        Order obj = dao.getById(id);
        if (obj != null) {
            Integer orderId = obj.getId();
            // also make sure to remove OrderDetails for this order
            OrderDetailsDao odDao = new OrderDetailsDao(dao.getSession());
            for (OrderDetails od : odDao.getByOrderId(orderId)) {
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

    @SuppressWarnings("unchecked")
    private <T> T getObject(BaseDao<?> dao, Map<String, Object> map, String field) throws IOException {
        if (!map.containsKey(field)) {
            throw new IOException("Missing " + field + " in order definition");
        }
        Object id = map.remove(field);
        T result = (T) dao.getById( id );
        if (result == null) {
            throw new IOException("No object found for " + field + " = " + id.toString());
        }
        return result;
    }

    private void ensureFloat(Map<String, Object> map, List<String> fields) {
        for (String f : fields) {
            Object v = map.get(f);
            Float newVal = null;
            if (v == null) {
                newVal = 0.0f;
            }
            else if (v instanceof Integer || v instanceof Double) {
                // ugly, but it works...
                newVal = Float.valueOf( String.valueOf(v) );
            }
            // update if needed
            if (newVal != null) {
                map.put(f, newVal);
            }
        }
    }
}
