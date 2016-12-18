package northwind.rest.app.dao;

import northwind.rest.app.model.Customer;

import java.util.List;

import org.hibernate.Session;

/**
 * DAO layer for customers.
 */
public class CustomerDao extends BaseDao<Customer> implements Dao<Customer> {
    public CustomerDao(Session... s) {
        super(Customer.class, String.class, s);
    }

    @Override
    public List<Customer> getAll() {
        return getAll("Customer");
    }

    @Override
    public Customer getById(Object id) {
        return getByIdInternal(id);
    }
}
