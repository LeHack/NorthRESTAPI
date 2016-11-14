package northwind.rest.app.dao;

import northwind.rest.app.model.Product;
import northwind.rest.app.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;

/**
 * DAO layer for products.
 */
public class ProductDao implements Dao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from Product";
        Query query = session.createQuery(hql);
        List<Product> products = query.list();

        session.getTransaction().commit();

        if (products == null) {
            return Collections.emptyList();
        }
        return products;
    }
}
