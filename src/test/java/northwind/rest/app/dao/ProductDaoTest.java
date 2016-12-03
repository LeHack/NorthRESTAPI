package northwind.rest.app.dao;

import northwind.rest.app.model.Category;
import northwind.rest.app.model.Product;
import northwind.rest.app.model.Supplier;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductDaoTest {

    private ProductDao productDao;

    @Before
    public void setUp() {
        productDao = new ProductDao();
    }

    @Test
    public void testSave_ShouldPersistNewProduct() throws Exception {
        Session session = null;
        Transaction transaction = null;

        Product retrivedProduct = null;
        Category category = null;
        Supplier supplier = null;
        Product product = null;

        try {
            session = productDao.openSession();
            transaction = session.beginTransaction();

            // load() return a proxy (fake object) with given id, not a real db object
            // be sure that object exist, get() hit the database and return real object
            category = session.load(Category.class, 1);
            supplier = session.load(Supplier.class, 2);

            product = new Product();
            product.setCategory(category);
            product.setSupplier(supplier);
            product.setDiscontinued(0);
            product.setName("Test product number 2");
            product.setQuantityPerUnit("10");
            product.setReorderLevel(1);
            product.setUnitPrice(99.99);
            product.setUnitsInStock(1000);
            product.setUnitsOnOrder(10);

            Integer productId = productDao.save(product);

            retrivedProduct = session.get(Product.class, productId);
            transaction.commit();
        } catch (RuntimeException re) {
            productDao.rollbackTransaction(transaction, re);
        } finally {
            productDao.closeSession();
        }

        Assert.assertNotNull(retrivedProduct);
        Assert.assertEquals(retrivedProduct.getCategory().getId(), category.getId());
        Assert.assertEquals(retrivedProduct.getSupplier().getId(), supplier.getId());
        Assert.assertEquals(retrivedProduct.getName(), product.getName());
    }

    @Test
    public void testUpdate_ShouldUpdateExistingProduct() throws Exception {
        Session session = null;
        Transaction transaction = null;

        Product product = null;
        Product expectedProduct = null;
        String name = "Update product nr 70";
        Double price = 245435.99;
        Integer discontinued = 1;
        try {
            session = productDao.openSession();
            transaction = session.beginTransaction();

            product = session.get(Product.class, 70);
            product.setName(name);
            product.setDiscontinued(discontinued);
            product.setUnitPrice(price);

            transaction.commit();

            expectedProduct = session.get(Product.class, 70);

        } catch (RuntimeException re) {
            productDao.rollbackTransaction(transaction, re);
        } finally {
            productDao.closeSession();
        }

        Assert.assertEquals(expectedProduct.getName(), name);
        Assert.assertEquals(expectedProduct.getUnitPrice(), price);
        Assert.assertEquals(expectedProduct.getDiscontinued(), discontinued);
    }

    @Test
    public void testDelete_ShouldRemoveProductFromDB() throws Exception {
        Session session = null;
        Transaction transaction = null;

        Product product = null;
        Integer existingProductId = persistTestProduct();
        try {
            session = productDao.openSession();
            transaction = session.beginTransaction();

            product = session.get(Product.class, existingProductId);
            System.out.println("Persist product object with id: " + existingProductId);
            Assert.assertNotNull(product);
            Assert.assertEquals(product.getId(), existingProductId);

            session.delete(product);
            transaction.commit();
        } catch (RuntimeException re) {
            productDao.rollbackTransaction(transaction, re);
        } finally {
            productDao.closeSession();
        }
    }

    private Integer persistTestProduct() {
        Session session = productDao.openSession();
        session.beginTransaction();

        Category category = session.load(Category.class, 1);
        Supplier supplier = session.load(Supplier.class, 2);

        Product product = new Product();
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setDiscontinued(0);
        product.setName("Test delete product");
        product.setQuantityPerUnit("10");
        product.setReorderLevel(1);
        product.setUnitPrice(99.99);
        product.setUnitsInStock(1000);
        product.setUnitsOnOrder(10);

        Integer id = (Integer) session.save(product);
        session.getTransaction().commit();
        productDao.closeSession();

        return id;
    }
}
