package northwind.rest.app.dao;

import northwind.rest.app.model.Shipper;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;

/**
 * DAO layer for shippers.
 */
public class ShipperDao extends BaseDao implements Dao<Shipper> {
    @Override
    public List<Shipper> getAll() {
        return getAll("Shipper", Shipper.class);
    }

    @Override
    public Shipper getById(Session session, Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("shipperId", id);
        return getByNamedQueryAndParam(session, "findShipperById", params);
    }
}
