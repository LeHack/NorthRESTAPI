package northwind.rest.app.dao;

import northwind.rest.app.model.Category;
import northwind.rest.app.model.Customer;
import northwind.rest.app.model.Employee;
import northwind.rest.app.model.Order;
import northwind.rest.app.model.Product;
import northwind.rest.app.model.Shipper;
import northwind.rest.app.model.Supplier;
import northwind.rest.app.util.HibernateUtil;
import northwind.rest.app.util.ListUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DaoTest {

    private Session session;

    @Before
    public void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }

    @After
    public void tearDown() {
        session.getTransaction().commit();
    }

    @Test
    public void testCategoryDao_shouldReturnAllRows() {
        String hql = "from Category";
        Query query = session.createQuery(hql);
        List<Category> categories = ListUtil.castList(Category.class, query.list());

        Assert.assertNotNull(categories);
    }

    @Test
    public void testSupplierDao_shouldReturnAllRows() {
        String hql = "from Supplier";
        Query query = session.createQuery(hql);
        List<Supplier> suppliers = ListUtil.castList(Supplier.class, query.list());

        Assert.assertNotNull(suppliers);
    }

    @Test
    public void testProductDao_shouldReturnAllRows() {
        String hql = "from Product";
        Query query = session.createQuery(hql);
        List<Product> products = ListUtil.castList(Product.class, query.list());

        Assert.assertNotNull(products);
    }

    @Test
    public void testEmployeesDao_shouldReturnAllRows() {
        String hql = "from Employee";
        Query query = session.createQuery(hql);
        List<Employee> employees = ListUtil.castList(Employee.class, query.list());

        Assert.assertNotNull(employees);
    }

    @Test
    public void testOrdersDao_shouldReturnAllRows() {
        String hql = "from Order";
        Query query = session.createQuery(hql);
        List<Order> orders = ListUtil.castList(Order.class, query.list());

        Assert.assertNotNull(orders);
    }

    @Test
    public void testCustomersDao_shouldReturnAllRows() {
        String hql = "from Customer";
        Query query = session.createQuery(hql);
        List<Customer> customers = ListUtil.castList(Customer.class, query.list());

        Assert.assertNotNull(customers);
    }

    @Test
    public void testShippersDao_shouldReturnAllRows() {
        String hql = "from Shipper";
        Query query = session.createQuery(hql);
        List<Shipper> shippers = ListUtil.castList(Shipper.class, query.list());

        Assert.assertNotNull(shippers);
    }
}
