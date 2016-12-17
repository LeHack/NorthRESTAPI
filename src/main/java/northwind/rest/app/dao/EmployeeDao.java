package northwind.rest.app.dao;

import northwind.rest.app.model.Employee;

import java.util.List;

import org.hibernate.Session;

/**
 * DAO layer for employees.
 */
public class EmployeeDao extends BaseDao<Employee> implements Dao<Employee> {
    public EmployeeDao(Session... s) {
        super(Employee.class, s);
    }

    @Override
    public List<Employee> getAll() {
        return getAll("Employee");
    }

    @Override
    public Employee getById(Integer id) {
        return getById(id);
    }

    @Override
    public Employee getById(Object id) {
        return getById((Integer)id);
    }
}
