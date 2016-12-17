package northwind.rest.app.dao;

import northwind.rest.app.model.OrderDetails;

import java.util.List;

import org.hibernate.Session;

/**
 * Dao layer OrderDetails.
 */
public class OrderDetailsDao extends BaseDao<OrderDetails> implements Dao<OrderDetails> {
    public OrderDetailsDao(Session... s) {
        super(OrderDetails.class, s);
    }

    @Override
    public List<OrderDetails> getAll() {
        return getAll("OrderDetails");
    }

    @Override
    public OrderDetails getById(Integer id) {
        return getById(id);
    }

    @Override
    public OrderDetails getById(Object id) {
        return getById((Integer)id);
    }
}
