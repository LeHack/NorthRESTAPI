package northwind.rest.app.dao;

import northwind.rest.app.model.Supplier;

import java.util.List;

import org.hibernate.Session;

/**
 * DAO layer for suppliers.
 */
public class SupplierDao extends BaseDao implements Dao<Supplier> {
    public SupplierDao(Session... s) {
        super(s);
    }

    public List<Supplier> getAll() {
        return getAll("Supplier", Supplier.class);
    }

    @Override
    public Supplier getById(Integer id) {
        return getById(Supplier.class, id);
    }

    public Supplier getById(Object id) {
        return getById((Integer)id);
    }
}
