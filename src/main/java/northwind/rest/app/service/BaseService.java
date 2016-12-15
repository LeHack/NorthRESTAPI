package northwind.rest.app.service;

import northwind.rest.app.dao.BaseDao;
import northwind.rest.app.dao.BaseDao.SessionNotAvailable;
import northwind.rest.app.model.Base;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.hibernate.Transaction;

public class BaseService {
    protected BaseDao dao;

    public <T> List<T> getAll() {
        List<T> list = null;
        dao.openSession();
        try {
            list = dao.getAll();
        } catch (SessionNotAvailable e) {
            list = Collections.emptyList();
        }
        dao.closeSession();
        return list;
    }

    public <T> T getOne(@PathParam("id")Integer id) {
        dao.openSession();
        T obj = dao.getById(id);
        dao.closeSession();
        return obj;
    }

    public <T> T getOne(@PathParam("id")String id) {
        dao.openSession();
        T obj = dao.getById(id);
        dao.closeSession();
        return obj;
    }

    public <T> Response createNew( T obj ) throws URISyntaxException {
        if(obj == null){
            return Response.status(400).entity("Please specify object to be created.").build();
        }

        Response r = validateNewObject(obj);
        if (r != null) {
            return r;
        }

        dao.openSession();
        dao.save(obj);
        dao.closeSession();

        return Response.status(200).entity("Object created OK.").build();
    }

    public <T> Response updateObject( T obj ) throws URISyntaxException {
        if(obj == null) {
            return Response.status(400).entity("Please specify details to update.").build();
        }
        else if (((Base)obj).getId() == null) {
            return Response.status(400).entity("You need to pass the id of the object to update.").build();
        }

        // check if this object is present in DB
        Response r = Response.status(200).entity("Object updated OK.").build();
        Transaction t = dao.openSession().beginTransaction();
        @SuppressWarnings("unchecked")
        T fromDb = (T)dao.getById(obj.getClass(), ((Base)obj).getId());
        if (fromDb != null) {
            dao.getSession().merge(obj);
            t.commit();
        }
        else {
            t.rollback();
            r = Response.status(400).entity("The object does not exist in DB.").build();
        }
        dao.closeSession();

        return r;
    }

    public <T> Response dropObject(@PathParam("id")Integer id) throws URISyntaxException {
        Response r = Response.status(200).entity("Object removed OK.").build();
        Transaction t = dao.openSession().beginTransaction();
        T obj = dao.getById(id);
        if (obj != null) {
            dao.delete(obj);
            t.commit();
        }
        else {
            t.rollback();
            r = Response.status(400).entity("The object does not exist in DB.").build();
        }
        dao.closeSession();
        return r;
    }

    protected <T> Response validateNewObject(T obj) { return null; };
}
