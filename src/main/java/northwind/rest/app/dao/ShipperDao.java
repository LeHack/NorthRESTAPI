package northwind.rest.app.dao;

import northwind.rest.app.model.Shipper;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

/**
 * DAO layer for shippers.
 */
public class ShipperDao extends BaseDao<Shipper> implements Dao<Shipper> {
    public ShipperDao(Session... s) {
        super(Shipper.class, s);
    }

    @Override
    public List<Shipper> getAll() {
        return getAll("Shipper");
    }

    @Override
    public Shipper getById(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("shipperId", id);
        return getByNamedQueryAndParam("findShipperById", params);
    }

    @Override
    public Shipper getById(Object id) {
        return getById((Integer)id);
    }
}
