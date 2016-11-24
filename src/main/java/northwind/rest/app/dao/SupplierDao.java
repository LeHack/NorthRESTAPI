package northwind.rest.app.dao;

import northwind.rest.app.model.Supplier;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * DAO layer for suppliers.
 */
public class SupplierDao extends BaseDao implements Dao<Supplier> {
    @Override
    public List<Supplier> getAll() {
        return getAll("Supplier", Supplier.class);
    }

    @Override
    public Supplier getById(Session session, Integer id) {
        List<Supplier> suppliers = getByCriteriaAndRestriction(
                session, Supplier.class, Restrictions.eq("id", id));
        return (suppliers == null) ? null : suppliers.get(0);
    }

}
