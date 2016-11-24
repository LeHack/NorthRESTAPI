package northwind.rest.app.dao;

import northwind.rest.app.model.Customer;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO layer for customers.
 */
public class CustomerDao extends BaseDao implements Dao<Customer> {
    @Override
    public List<Customer> getAll() {
        return getAll("Customer", Customer.class);
    }

    @Override
    public Customer getById(Session session, Integer id) {
        return null;
    }
}
