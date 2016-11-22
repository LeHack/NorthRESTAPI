package northwind.rest.app.dao;

import northwind.rest.app.model.Order;
import java.util.List;

/**
 * DAO layer for orders.
 */
public class OrderDao extends BaseDao implements Dao {
    @Override
    public List<Order> getAll() {
        return getAll("Order", Order.class);
    }
}
