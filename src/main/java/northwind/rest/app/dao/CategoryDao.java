package northwind.rest.app.dao;

import northwind.rest.app.model.Category;
import northwind.rest.app.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;

/**
 * DAO layer for categories.
 */
public class CategoryDao implements Dao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Category> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from Category";
        Query query = session.createQuery(hql);
        List<Category> categories = query.list();

        session.getTransaction().commit();

        if (categories == null) {
            return Collections.emptyList();
        }
        return categories;
    }
}
