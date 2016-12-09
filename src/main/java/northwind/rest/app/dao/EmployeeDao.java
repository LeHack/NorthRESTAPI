package northwind.rest.app.dao;

import northwind.rest.app.model.Employee;

import java.util.List;

import org.hibernate.Session;

/**
 * DAO layer for employees.
 */
public class EmployeeDao extends BaseDao implements Dao<Employee> {
    public EmployeeDao(Session... s) {
        super(s);
    }

    public List<Employee> getAll() {
        return getAll("Employee", Employee.class);
    }

    @Override
    public Employee getById(Integer id) {
        return getById(Employee.class, id);
    }

    public Employee getById(Object id) {
        return getById((Integer)id);
    }
}
