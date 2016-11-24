package northwind.rest.app.dao;

import northwind.rest.app.model.Employee;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO layer for employees.
 */
public class EmployeeDao extends BaseDao implements Dao<Employee> {
    @Override
    public List<Employee> getAll() {
        return getAll("Employee", Employee.class);
    }

    @Override
    public Employee getById(Session session, Integer id) {
        return null;
    }
}
