package northwind.rest.app.dao;

import northwind.rest.app.model.Category;
import northwind.rest.app.model.Product;
import northwind.rest.app.model.Supplier;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductDaoTest {

    private ProductDao productDao;

    private Session session;

    @Before
    public void setUp() {
        productDao = new ProductDao();
        session = productDao.getSession();
    }

    @After
    public void tearDown() {
        session.close();
    }

    @Test
    public void testSave_ShouldPersistNewProduct() throws Exception {
        Category category = session.get(Category.class, 1);
        Supplier supplier = session.get(Supplier.class, 2);

        Product product = new Product();
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setDiscontinued(0);
        product.setName("Test product number 1");
        product.setQuantityPerUnit("10");
        product.setReorderLevel(1);
        product.setUnitPrice(99.99);
        product.setUnitsInStock(1000);
        product.setUnitsOnOrder(10);

        Integer productId = productDao.save(session, product);

        Product retrivedProduct = session.get(Product.class, productId);

        Assert.assertNotNull(retrivedProduct);
        Assert.assertEquals(retrivedProduct.getCategory().getId(), category.getId());
        Assert.assertEquals(retrivedProduct.getSupplier().getId(), supplier.getId());
        Assert.assertEquals(retrivedProduct.getName(), product.getName());
    }

}
