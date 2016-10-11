package northwind.rest.app.dao;

import northwind.rest.app.model.Category;
import northwind.rest.app.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DaoTest {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Test
    public void testDao_shouldReturnAllRows() {
        String hql = "from Category";
        Query query = session.createQuery(hql);
        List<Category> categories = query.list();

        Assert.assertNotNull(categories);
        Assert.assertEquals("Should be 8 records", 8, categories.size());
    }
}
