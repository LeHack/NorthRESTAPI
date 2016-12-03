package northwind.rest.app.dao;

import northwind.rest.app.model.Customer;

import java.util.List;

/**
 * DAO layer for customers.
 */
public class CustomerDao extends BaseDao implements Dao<Customer> {

    public List<Customer> getAll() {
        return getAll("Customer", Customer.class);
    }

    @Override
    public Customer getById(String id) {
        return getById(Customer.class, id);
    }

    public Customer getById(Object id) {
        return getById((String)id);
    }
}
