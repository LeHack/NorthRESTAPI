package northwind.rest.app.dao;

import northwind.rest.app.model.Shipper;
import java.util.List;

/**
 * DAO layer for shippers.
 */
public class ShipperDao extends BaseDao implements Dao {
    @Override
    public List<Shipper> getAll() {
        return getAll("Shipper", Shipper.class);
    }
}
