package northwind.rest.app.dao;

import northwind.rest.app.model.Product;
import java.util.List;

/**
 * DAO layer for products.
 */
public class ProductDao extends BaseDao implements Dao {
    @Override
    public List<Product> getAll() {
        return getAll("Product", Product.class);
    }
}
