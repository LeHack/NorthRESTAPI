package northwind.rest.app.dao;

import northwind.rest.app.model.OrderDetails;

import java.util.List;

/**
 * Dao layer OrderDetails.
 */
public class OrderDetailsDao extends BaseDao implements Dao {

    @Override
    public List<OrderDetails> getAll() {
        return getAll("OrderDetails", OrderDetails.class);
    }
}
