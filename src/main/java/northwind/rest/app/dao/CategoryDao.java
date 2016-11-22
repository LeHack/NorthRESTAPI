package northwind.rest.app.dao;

import northwind.rest.app.model.Category;
import java.util.List;

/**
 * DAO layer for categories.
 */
public class CategoryDao extends BaseDao implements Dao {
    @Override
    public List<Category> getAll() {
        return getAll("Category", Category.class);
    }
}
