package northwind.rest.app.dao;

import northwind.rest.app.model.OrderDetails;

import java.util.List;

import org.hibernate.Session;

/**
 * Dao layer OrderDetails.
 */
public class OrderDetailsDao extends BaseDao implements Dao<OrderDetails> {
    public OrderDetailsDao(Session... s) {
        super(s);
    }

    public List<OrderDetails> getAll() {
        return getAll("OrderDetails", OrderDetails.class);
    }

    @Override
    public OrderDetails getById(Integer id) {
        return getById(OrderDetails.class, id);
    }

    public OrderDetails getById(Object id) {
        return getById((Integer)id);
    }
}
