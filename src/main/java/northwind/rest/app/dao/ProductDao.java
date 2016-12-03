package northwind.rest.app.dao;

import northwind.rest.app.model.Product;

import java.util.List;

/**
 * DAO layer for products.
 */
public class ProductDao extends BaseDao implements Dao<Product> {

    public List<Product> getAll() {
        return getAll("Product", Product.class);
    }

    @Override
    public Product getById(Integer id) {
        return getById(Product.class, id);
    }

    public Product getById(Object id) {
        return getById((Integer)id);
    }
}
