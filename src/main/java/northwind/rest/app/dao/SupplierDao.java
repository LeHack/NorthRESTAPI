package northwind.rest.app.dao;

import northwind.rest.app.model.Supplier;

import java.util.List;

import org.hibernate.Session;

/**
 * DAO layer for suppliers.
 */
public class SupplierDao extends BaseDao<Supplier> implements Dao<Supplier> {
    public SupplierDao(Session... s) {
        super(Supplier.class, s);
    }

    @Override
    public List<Supplier> getAll() {
        return getAll("Supplier");
    }

    @Override
    public Supplier getById(Integer id) {
        return getById(id);
    }

    @Override
    public Supplier getById(Object id) {
        return getById((Integer)id);
    }
}
