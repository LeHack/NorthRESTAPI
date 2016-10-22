package northwind.rest.app.dao;

import java.util.List;


public interface Dao<E> {

    List<E> get();

}
