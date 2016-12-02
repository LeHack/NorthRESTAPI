package northwind.rest.app.dao;

import northwind.rest.app.model.Product;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * DAO layer for products.
 */
public class ProductDao extends BaseDao implements Dao<Product> {
    @Override
    public List<Product> getAll() {
        return getAll("Product", Product.class);
    }

    @Override
    public Product getById(Session session, Integer id) {
        List<Product> products = getByCriteriaAndRestriction(
                session, Product.class, Restrictions.eq("id", id));
        return (products.isEmpty() ? null : products.get(0));
    }
}
