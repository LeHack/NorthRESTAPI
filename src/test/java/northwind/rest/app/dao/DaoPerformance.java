package northwind.rest.app.dao;

import static org.junit.Assert.*;

import northwind.rest.app.model.Category;
import northwind.rest.app.model.Customer;
import northwind.rest.app.model.Employee;
import northwind.rest.app.model.Order;
import northwind.rest.app.model.Product;
import northwind.rest.app.model.Shipper;
import northwind.rest.app.model.Supplier;
import northwind.rest.app.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoPerformance {

    private static String testDbName;
    private static SessionFactory sFactory;
    private Session session;

    /**
     * For this to work, the user that you use to connect to the DB
     * must have superuser privileges (so that he can create/drop DBs) 
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        testDbName = "test_northrestapi_db_" + System.currentTimeMillis();
        String dbUrl = Common.createTestDB(testDbName);
        // now update the session factory
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.setProperty("hibernate.connection.url", dbUrl);
        sFactory = cfg.buildSessionFactory();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // drop test DB
        sFactory.close();
        Common.dropTestDB(testDbName);
    }

    @Before
    public void setUp() {
        session = sFactory.openSession();
    }

    @After
    public void tearDown() {
        session.close();
    }

    @Test
    public void testCategoryDao_01_create1k() {
        testCategoryDao_create(1000);
    }

    @Test
    public void testCategoryDao_01_read1k() {
        testCategoryDao_read(1000);
    }

    @Test
    public void testCategoryDao_02_create10k() {
        testCategoryDao_create(10000);
    }

    @Test
    public void testCategoryDao_02_read10k() {
        testCategoryDao_read(10000);
    }

    private void testCategoryDao_create(long count) {
        CategoryDao dao = new CategoryDao(session);
        session.beginTransaction();
        for (int i = 1; i <= count; i++) {
            Category c = new Category();
            c.setName("Category " + i);
            c.setDescription("Some interesting category, one of a kind!");
            dao.save(c);
        }
        session.getTransaction().commit();
    }

    private void testCategoryDao_read(long count) {
        CategoryDao dao = new CategoryDao(session);
        List<Category> categories = dao.getAll();
        assertNotNull(categories);
        assertTrue(categories.size() > count);
    }

    @Test
    public void testCustomerDao_01_create1k() {
        testCustomerDao_create("CustA", 1000);
    }

    @Test
    public void testCustomerDao_01_read1k() {
        testCustomerDao_read(1000);
    }

    @Test
    public void testCustomerDao_02_create10k() {
        testCustomerDao_create("CustB", 10000);
    }

    @Test
    public void testCustomerDao_02_read10k() {
        testCustomerDao_read(10000);
    }

    @Test
    public void testCustomerDao_03_create100k() {
        testCustomerDao_create("CustC", 100000);
    }

    @Test
    public void testCustomerDao_03_read100k() {
        testCustomerDao_read(100000);
    }

    private void testCustomerDao_create(String custPrefix, long count) {
        CustomerDao dao = new CustomerDao(session);
        session.beginTransaction();
        for (int i = 1; i <= count; i++) {
            Customer c = new Customer();
            c.setId(custPrefix + i);
            c.setContactName("Customer " + i);
            c.setCompanyName("Company " + i);
            dao.save(c);
        }
        session.getTransaction().commit();
    }

    private void testCustomerDao_read(long count) {
        CustomerDao dao = new CustomerDao(session);
        List<Customer> customers = dao.getAll();
        assertNotNull(customers);
        assertTrue(customers.size() > count);
    }
}
