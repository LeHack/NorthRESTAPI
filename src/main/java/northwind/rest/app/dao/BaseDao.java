package northwind.rest.app.dao;

import northwind.rest.app.exceptions.SessionNotAvailable;
import northwind.rest.app.util.HibernateUtil;
import northwind.rest.app.util.ListUtil;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO base object.
 */
public abstract class BaseDao<T> {
    final Class<T> usedClass;
    final Class<?> idType;

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected Session session = null;

    public BaseDao(Class<T> cls, Class<?> idCls) {
        this.usedClass = cls;
        this.idType    = idCls;
    }

    // Default save return type is Integer
    public BaseDao(Class<T> cls, Session... s) {
        this(cls, Integer.class, s);
    }

    // allow for using an already open session
    public BaseDao(Class<T> cls, Class<?> idCls, Session... s) {
        this(cls, idCls);
        if (s.length > 0) {
            if (!s[0].isOpen()) {
                throw new RuntimeException("The passed session object does not contain an open session!");
            }
            this.session = s[0];
        }
    }

    public abstract List<T> getAll();
    public List<T> getAll(String tableName) {
        Session session = getSession();
        session.beginTransaction();

        String hql = "from " + tableName;
        Query query = session.createQuery(hql);
        List<T> items = ListUtil.castList(this.usedClass, query.list());

        session.getTransaction().commit();

        if (items == null) {
            items = Collections.emptyList();
        }
        return items;
    }

    public abstract T getById(Object id);
    protected T getByIdInternal(Object id) {
        List<T> objects = getByCriteriaAndRestriction( Restrictions.eq("id", id) );
        return (objects.isEmpty() ? null : objects.get(0));
    }

    protected List<T> getBy(SimpleExpression r) {
        return getByCriteriaAndRestriction( r );
    }

    public T getByNamedQueryAndParam(String namedQuery, HashMap<String, Object> params) {
        Query query = getSession().getNamedQuery(namedQuery);
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List<T> items = ListUtil.castList(this.usedClass, query.list());
        return (items.isEmpty() ? null : items.get(0));
    }

    public List<T> getByCriteriaAndRestriction(SimpleExpression simpleExpression) {
        Criteria criteria = getSession().createCriteria(this.usedClass).add(simpleExpression);
        List<T> items = ListUtil.castList(this.usedClass, criteria.list());
        return (items.isEmpty() ? Collections.emptyList() : items);
    }

    public void rollbackTransaction(Transaction transaction, RuntimeException exp) throws RuntimeException {
        try {
            transaction.rollback();
        } catch (RuntimeException re) {
            System.err.println("Couldn’t roll back transaction " + re);
        }
        throw exp;
    }

    @SuppressWarnings("unchecked")
    public <idCls> idCls save(T entity) {
        return (idCls) getSession().save(entity);
    }

    public void update(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public void delete(T entity) {
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

    public Class<?> getAttributeType(String attr) throws NoSuchMethodException {
        Class<?> result = String.class;
        String ucFirst = attr.substring(0, 1).toUpperCase() + attr.substring(1);
        Method get;
        try {
            get = this.usedClass.getMethod("get" + ucFirst);
            result = get.getReturnType();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return result;
    }
}
