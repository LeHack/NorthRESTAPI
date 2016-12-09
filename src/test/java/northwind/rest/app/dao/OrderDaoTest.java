package northwind.rest.app.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import northwind.rest.app.model.Customer;
import northwind.rest.app.model.Employee;
import northwind.rest.app.model.Order;
import northwind.rest.app.model.Shipper;

public class OrderDaoTest {

    private OrderDao orderDao;

    @Before
    public void setUp() {
        orderDao = new OrderDao();
    }

    @Test
    public void testSave_ShouldPersistNewOrder() throws Exception {
        Session session = null;
        Transaction transaction = null;

        Order retrievedOrder = null;
        Customer customer = null;
        Shipper shipper = null;
        Order order = null;
        Employee employee = null;

        try {
            session = orderDao.openSession();
            transaction = session.beginTransaction();

            // load() return a proxy (fake object) with given id, not a real db object
            // be sure that object exist, get() hit the database and return real object
            customer = session.load(Customer.class, "ALFKI");
            shipper = session.load(Shipper.class, 1);
            employee = session.load(Employee.class, 3);

            order = new Order();
            order.setCustomer(customer);
            order.setShipVia(shipper);
            order.setEmployee(employee);
            order.setFreight(2.51);
            order.setShipName("special order");
            order.setOrderDate(Common.asDate("2015-05-25"));
            order.setShippedDate(Common.asDate("2016-01-02"));
            order.setShipAddress("Somewhere street");
            order.setShipCity("Shiperville");

            Integer orderId = orderDao.save(order);

            retrievedOrder = session.get(Order.class, orderId);
            transaction.commit();
        } catch (RuntimeException re) {
            orderDao.rollbackTransaction(transaction, re);
        } finally {
            orderDao.closeSession();
        }

        assertNotNull(retrievedOrder);
        assertEquals(retrievedOrder.getCustomer().getId(), customer.getId());
        assertEquals(retrievedOrder.getEmployee().getId(), employee.getId());
        assertEquals(retrievedOrder.getShipVia().getId(), shipper.getId());
        assertEquals(retrievedOrder.getShipName(), order.getShipName());
    }

    @Test
    public void testUpdate_ShouldUpdateExistingOrder() throws Exception {
        Session session = null;
        Transaction transaction = null;

        Order order = null;
        Order expectedOrder = null;
        Customer customer = null;
        Employee employee = null;
        Date shippedDate = Common.asDate("1996-07-16");
        String shipName = "Vins et alcools Chevalier";
        try {
            session = orderDao.openSession();
            transaction = session.beginTransaction();

            customer = session.load(Customer.class, "ALFKI");
            employee = session.load(Employee.class, 5);

            order = session.get(Order.class, 10248);
            order.setCustomer(customer);
            order.setEmployee(employee);
            order.setShippedDate(shippedDate);
            order.setShipName(shipName);

            transaction.commit();

            expectedOrder = session.get(Order.class, 10248);

            assertEquals(expectedOrder.getCustomer().getContactName(), customer.getContactName());
            assertEquals(expectedOrder.getEmployee().getLastName(), employee.getLastName());
            assertEquals(expectedOrder.getShippedDate(), shippedDate);
            assertEquals(expectedOrder.getShipName(), shipName);
        } catch (RuntimeException re) {
            orderDao.rollbackTransaction(transaction, re);
        } finally {
            orderDao.closeSession();
        }
        assertNotNull(expectedOrder);
    }

    @Test
    public void testDelete_ShouldRemoveOrderFromDB() throws Exception {
        Session session = null;
        Transaction transaction = null;

        Order order = null;
        int existingOrderId = persistTestOrder();
        try {
            session = orderDao.openSession();
            transaction = session.beginTransaction();

            order = session.get(Order.class, existingOrderId);
            System.out.println("Persist order object with id: " + existingOrderId);
            assertNotNull(order);
            assertEquals(order.getId(), existingOrderId);

            session.delete(order);
            transaction.commit();

            order = session.get(Order.class, existingOrderId);
            assertNull(order);
        } catch (RuntimeException re) {
            orderDao.rollbackTransaction(transaction, re);
        } finally {
            orderDao.closeSession();
        }
    }

    private Integer persistTestOrder() {
        Session session = orderDao.openSession();
        session.beginTransaction();

        Customer customer = session.load(Customer.class, "ALFKI");
        Shipper shipper = session.load(Shipper.class, 1);
        Employee employee = session.load(Employee.class, 3);

        Order order = new Order();
        order.setCustomer(customer);
        order.setShipVia(shipper);
        order.setEmployee(employee);
        order.setFreight(2.51);
        order.setShipName("special order");
        order.setOrderDate(Common.asDate("2015-05-25"));
        order.setShippedDate(Common.asDate("2016-01-02"));
        order.setShipAddress("Somewhere street");
        order.setShipCity("Shiperville");

        Integer id = (Integer) session.save(order);
        session.getTransaction().commit();

        return id;
    }
}
