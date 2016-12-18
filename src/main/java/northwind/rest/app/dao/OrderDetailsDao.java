package northwind.rest.app.dao;

import northwind.rest.app.model.OrderDetails;
import northwind.rest.app.util.ListUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
    public OrderDetails getById(Object id) {
        return getByIdInternal(id);
    }
        
    public List<OrderDetails> getByOrderId(Integer orderId) {
        return ListUtil.castList(OrderDetails.class,
            getSession().createCriteria(OrderDetails.class)
                .add(Restrictions.eq("id.order.id", orderId))
                .list()
        );
    }

    public List<OrderDetails> getByProductId(Integer productId) {
        return ListUtil.castList(OrderDetails.class,
            getSession().createCriteria(OrderDetails.class)
                .add(Restrictions.eq("id.product.id", productId))
                .list()
        );
    }
}
