package northwind.rest.app.dao;

import northwind.rest.app.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;

/**
 * DAO layer for products.
 */
public class BaseDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public <T> List<T> getAll(String tableName, Class<T> contentType) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from " + tableName;
        Query query = session.createQuery(hql);
        List<T> items = query.list();

        session.getTransaction().commit();

        if (items == null) {
            items = Collections.emptyList();
        }
        return items;
    }
}
