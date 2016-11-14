package northwind.rest.app.dao;

import java.util.List;

/**
 * Common interface for all DAO objects.
 *
 * @param <E>
 */
public interface Dao<E> {

    List<E> getAll();
}
