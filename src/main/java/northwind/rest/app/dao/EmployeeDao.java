package northwind.rest.app.dao;

import northwind.rest.app.model.Employee;
import java.util.List;

/**
 * DAO layer for employees.
 */
public class EmployeeDao extends BaseDao implements Dao {
    @Override
    public List<Employee> getAll() {
        return getAll("Employee", Employee.class);
    }
}
