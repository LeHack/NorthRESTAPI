package northwind.rest.app.dao;

import java.util.List;

import northwind.rest.app.exceptions.SessionNotAvailable;

/**
 * Common interface for all DAO objects.
 *
 * @param <E>
 */
public interface Dao<E> {

    List<E> getAll() throws SessionNotAvailable;

    E getById(Object id) throws SessionNotAvailable;
}
