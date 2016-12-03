package northwind.rest.app.dao;

import northwind.rest.app.model.Shipper;

import java.util.HashMap;
import java.util.List;

/**
 * DAO layer for shippers.
 */
public class ShipperDao extends BaseDao implements Dao<Shipper> {

    public List<Shipper> getAll() {
        return getAll("Shipper", Shipper.class);
    }

    @Override
    public Shipper getById(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("shipperId", id);
        return getByNamedQueryAndParam("findShipperById", params);
    }

    public Shipper getById(Object id) {
        return getById((Integer)id);
    }
}
