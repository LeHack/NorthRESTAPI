package northwind.rest.app.dao;

import northwind.rest.app.model.Order;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO layer for orders.
 */
public class OrderDao extends BaseDao implements Dao<Order> {
    @Override
    public List<Order> getAll() {
        return getAll("Order", Order.class);
    }

    @Override
    public Order getById(Session session, Integer id) {
        return null;
    }
}
