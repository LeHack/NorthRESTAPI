package northwind.rest.app.dao;

import northwind.rest.app.model.OrderDetails;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Dao layer OrderDetails.
 */
public class OrderDetailsDao extends BaseDao implements Dao<OrderDetails> {

    @Override
    public List<OrderDetails> getAll() {
        return getAll("OrderDetails", OrderDetails.class);
    }

    @Override
    public OrderDetails getById(Session session, Integer id) {
        List<OrderDetails> orderDetails = getByCriteriaAndRestriction(
                session, OrderDetails.class, Restrictions.eq("id", id));
        return (orderDetails == null) ? null : orderDetails.get(0);
    }
}
