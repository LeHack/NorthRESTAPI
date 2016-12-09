package northwind.rest.app.dao;

import northwind.rest.app.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO base object.
 */
public abstract class BaseDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected Session session = null;

    // allow for using an already open session
    public BaseDao(Session... s) {
        if (s.length > 0) {
            if (!s[0].isOpen()) {
                throw new RuntimeException("The passed session object does not contain an open session!");
            }
            this.session = s[0];
        }
    }

    public abstract <T> List<T> getAll() throws SessionNotAvailable;
    public <T> List<T> getAll(String tableName, Class<T> contentType) {
        Session session = getSession();
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

    public <T> T getById(Integer id) { T o = null; return o; };
    public <T> T getById(String id)  { T o = null; return o; };
    public <T> T getById(Class<T> cls, Object id) {
        List<T> objects = getByCriteriaAndRestriction( cls, Restrictions.eq("id", id) );
        return (objects.isEmpty() ? null : objects.get(0));
    }

    public <T> T getByNamedQueryAndParam(String namedQuery, HashMap<String, Object> params) {
        Query query = getSession().getNamedQuery(namedQuery);
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List<T> items = query.list();
        return (items == null) ? null : items.get(0);
    }

    public <T> List<T> getByCriteriaAndRestriction(Class<T> tClass, SimpleExpression simpleExpression) {
        Criteria criteria = getSession().createCriteria(tClass).add(simpleExpression);
        List<T> items = criteria.list();
        return (items == null) ? Collections.emptyList() : items;
    }

    public void rollbackTransaction(Transaction transaction, RuntimeException exp) throws RuntimeException {
        try {
            transaction.rollback();
        } catch (RuntimeException re) {
            System.err.println("Couldn’t roll back transaction " + re);
        }
        throw exp;
    }

    public <T> Integer save(T entity) {
        return (Integer) getSession().save(entity);
    }

    public <T> void update(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public <T> void delete(T entity) {
        getSession().delete(entity);
    }

    public Session openSession() {
        if (session == null)
            session = sessionFactory.openSession();
        return session;
    }

    public void closeSession() {
        if (session != null) {
            session.close();
            session = null;
        }
    }

    public Session getSession() {
        if (session == null)
            throw new SessionNotAvailable();
        return session;
    }

    public class SessionNotAvailable extends RuntimeException {}
}
