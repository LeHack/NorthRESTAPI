package northwind.rest.app.dao;

import northwind.rest.app.model.Order;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Reporting and searching queries
 */
public class SearchTest {

    private OrderDao orderDao = new OrderDao();

    /**
     * Znajdz zamowienia zlozone pomiedzy lipiec 1996 a styczen 1997
     * wyslane do jednego z nstp krajow: France, Germany, Switzerland
     * Uporzadkuj po dacie wyslania
     */
    @Test
    public void findOrdersBetweenDatesWithCountries() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        Date startDate = formatter.parse("1996-07-01");
        Date endDate = formatter.parse("1997-01-01");

        List<Order> orders = orderDao.getSession().createCriteria(Order.class)
                        .add(Restrictions.in("shipCountry",
                                new String[]{"France", "Germany", "Switzerland"}))
                        .add(Restrictions.ge("orderDate", startDate))
                        .add(Restrictions.le("orderDate", endDate))
                        .addOrder(org.hibernate.criterion.Order.asc("shippedDate"))
                        .list();

        Assert.assertNotNull(orders);

        for (Order order : orders) {
            System.out.println("Order id: " + order.getId()
                    + ", order date: " + order.getOrderDate()
                    + ", ship country: " + order.getShipCountry());
        }
        System.out.println("Founded orders: " + orders.size());
    }

    /**
     * Znajdz zamowienia z data wyslania wieksza od lipiec 1996
     * ktorego klientem jest Manager (jakikolwiek) z nstp miast:
     * Reims, London LUB Owner z miast: Madrid, Paris
     * Uporzadkuj wedlug tytulu klienta
     */
    @Test
    public void findOrderWithDateByContactTitle() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        Date startDate = formatter.parse("1996-07-01");

        List<Order> orders = orderDao.getSession().createCriteria(Order.class)
                            .add(Restrictions.gt("shippedDate", startDate))
                            .createCriteria("customer")
                                .add(Restrictions.or(
                                        Restrictions.and(Restrictions.like("contactTitle", "%Manager%"),
                                                Restrictions.in("city", new String[]{"Reims", "London"})),
                                        Restrictions.and(Restrictions.like("contactTitle", "%Owner%"),
                                                Restrictions.in("city", new String[]{"Madrid", "Paris"})))
                                )
                            .addOrder(org.hibernate.criterion.Order.asc("contactTitle"))
                            .list();

        Assert.assertNotNull(orders);
        System.out.println("Founded orders: " + orders.size());
    }


    /**
     * Wyszukaj zamowienia dla ktorych laczna ilosc zamowionych
     * jednostek jest wieksza niz 50 a klienci pochodza z UK
     */
    @Test
    public void findOrderWithConditionByQuantityAndCountry() throws Exception {
        Session session = orderDao.getSession();
        Criteria criteria = session.createCriteria(Order.class);
    }
}
