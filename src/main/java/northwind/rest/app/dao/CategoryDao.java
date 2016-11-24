package northwind.rest.app.dao;

import northwind.rest.app.model.Category;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO layer for categories.
 */
public class CategoryDao extends BaseDao implements Dao<Category> {
    @Override
    public List<Category> getAll() {
        return getAll("Category", Category.class);
    }

    @Override
    public Category getById(Session session, Integer id) {
        return null;
    }
}
