package northwind.rest.app.dao;

import northwind.rest.app.model.Product;

import java.util.List;

import org.hibernate.Session;

/**
 * DAO layer for products.
 */
public class ProductDao extends BaseDao<Product> implements Dao<Product> {
    public ProductDao(Session... s) {
        super(Product.class, s);
    }

    @Override
    public List<Product> getAll() {
        return getAll("Product");
    }

    @Override
    public Product getById(Integer id) {
        return getById(id);
    }

    @Override
    public Product getById(Object id) {
        return getById((Integer)id);
    }
}
