package northwind.rest.app.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * Common interface for all DAO objects.
 *
 * @param <E>
 */
public interface Dao<E> {

    List<E> getAll();

    E getById(Session session, Integer id);
}
