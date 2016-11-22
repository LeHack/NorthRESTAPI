package northwind.rest.app.dao;

import northwind.rest.app.model.Customer;
import java.util.List;

/**
 * DAO layer for customers.
 */
public class CustomerDao extends BaseDao implements Dao {
    @Override
    public List<Customer> getAll() {
        return getAll("Customer", Customer.class);
    }
}
