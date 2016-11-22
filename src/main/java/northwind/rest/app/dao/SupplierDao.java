package northwind.rest.app.dao;

import northwind.rest.app.model.Supplier;
import java.util.List;

/**
 * DAO layer for suppliers.
 */
public class SupplierDao extends BaseDao implements Dao {
    @Override
    public List<Supplier> getAll() {
        return getAll("Supplier", Supplier.class);
    }
}
