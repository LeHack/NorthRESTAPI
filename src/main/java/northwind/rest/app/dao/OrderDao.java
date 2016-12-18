package northwind.rest.app.dao;

import northwind.rest.app.model.Customer;
import northwind.rest.app.model.Employee;
import northwind.rest.app.model.Order;
import northwind.rest.app.model.Shipper;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * DAO layer for orders.
 */
public class OrderDao extends BaseDao<Order> implements Dao<Order> {
    public OrderDao(Session... s) {
        super(Order.class, s);
    }

    @Override
    public List<Order> getAll() {
        return getAll("Order");
    }

    @Override
    public Order getById(Object id) {
        return getByIdInternal(id);
    }

    public List<Order> getByCustomer(Customer c) {
        return getBy(Restrictions.eq("customer", c));
    }

    public List<Order> getByEmployee(Employee e) {
        return getBy(Restrictions.eq("employee", e));
    }

    public List<Order> getByShipper(Shipper s) {
        return getBy(Restrictions.eq("shipVia", s));
    }
}
