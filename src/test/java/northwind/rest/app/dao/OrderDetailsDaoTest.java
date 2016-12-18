package northwind.rest.app.dao;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import northwind.rest.app.model.OrderDetails;

public class OrderDetailsDaoTest {

    private OrderDetailsDao dao;

    @Before
    public void setUp() {
        dao = new OrderDetailsDao();
    }

    @Test
    public void test_getByOrderId() throws Exception {
        Integer orderId = 10248;
        try {
            dao.openSession();

            List<OrderDetails> details = dao.getByOrderId(orderId);
            assertFalse(details.isEmpty());
        } finally {
            dao.closeSession();
        }
    }

    @Test
    public void test_getByProductId() throws Exception {
        Integer productId = 35;
        try {
            dao.openSession();

            List<OrderDetails> details = dao.getByProductId(productId);
            assertFalse(details.isEmpty());
        } finally {
            dao.closeSession();
        }
    }
}
