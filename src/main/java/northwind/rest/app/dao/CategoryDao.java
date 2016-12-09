package northwind.rest.app.dao;

import northwind.rest.app.model.Category;

import java.util.List;

import org.hibernate.Session;

/**
 * DAO layer for categories.
 */
public class CategoryDao extends BaseDao implements Dao<Category> {
    public CategoryDao(Session... s) {
        super(s);
    }

    public List<Category> getAll() {
        return getAll("Category", Category.class);
    }

    @Override
    public Category getById(Integer id) {
        return getById(Category.class, id);
    }

    public Category getById(Object id) {
        return getById((Integer)id);
    }
}
