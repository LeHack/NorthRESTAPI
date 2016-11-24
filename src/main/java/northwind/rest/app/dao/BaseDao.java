package northwind.rest.app.dao;

import northwind.rest.app.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.SimpleExpression;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO base object.
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

    public <T> T getByNamedQueryAndParam(Session session, String namedQuery, HashMap<String, Object> params) {
        Query query = session.getNamedQuery(namedQuery);
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List<T> items =  query.list();
        return (items == null) ? null : items.get(0);
    }

    public <T> List<T> getByCriteriaAndRestriction(Session session, Class<T> tClass, SimpleExpression simpleExpression) {
        Criteria criteria = session.createCriteria(tClass).add(simpleExpression);
        List<T> items = criteria.list();
        return (items == null) ? Collections.emptyList() : items;
    }

    public Long getSize(Session session, Class clazz) {
        Criteria criteria = session.createCriteria(clazz);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public <T> Integer save(Session session, T entity) {
        return (Integer) session.save(entity);
    }

    public <T> void update(Session session, T entity) {
        session.saveOrUpdate(entity);
    }

    public <T> void delete(Session session, T entity) {
        session.delete(entity);
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
