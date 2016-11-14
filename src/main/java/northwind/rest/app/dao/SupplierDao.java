package northwind.rest.app.dao;

import northwind.rest.app.model.Supplier;
import northwind.rest.app.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;

/**
 * DAO layer for suppliers.
 */
public class SupplierDao implements Dao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Supplier> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from Supplier";
        Query query = session.createQuery(hql);
        List<Supplier> suppliers = query.list();

        session.getTransaction().commit();

        if (suppliers == null) {
            return Collections.emptyList();
        }
        return suppliers;
    }
}
