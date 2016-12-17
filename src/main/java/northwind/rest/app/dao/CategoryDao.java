package northwind.rest.app.dao;

import northwind.rest.app.model.Category;

import java.util.List;

import org.hibernate.Session;

/**
 * DAO layer for categories.
 */
public class CategoryDao extends BaseDao<Category> implements Dao<Category> {
    public CategoryDao(Session... s) {
        super(Category.class, s);
    }

    @Override
    public List<Category> getAll() {
        return getAll("Category");
    }

    @Override
    public Category getById(Integer id) {
        return getById(id);
    }
}
