package northwind.rest.app.service;

import northwind.rest.app.dao.BaseDao;
import northwind.rest.app.dao.BaseDao.SessionNotAvailable;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.PathParam;

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
}
