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
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class DaoPerformance {

    private static String testDbName;
    private static SessionFactory sFactory;
    private static Connection dbConn;
    private Session session;

    /**
     * For this to work, the user that you use to connect to the DB
     * must have superuser privileges (so that he can create/drop DBs) 
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // get connection string from hibernate using the session factory
        sFactory = HibernateUtil.getSessionFactory();
        Properties props = ((SessionFactoryImpl)sFactory).getProperties();
        String
            url  = props.get("hibernate.connection.url").toString(),
            user = props.get("hibernate.connection.username").toString(),
            pass = props.get("hibernate.connection.password").toString();

        // create a new temporary test database
        testDbName = "test_northrestapi_db_" + System.currentTimeMillis();
        String updatedUrl = url.replaceAll("northrest", testDbName);
        dbConn = DriverManager.getConnection(url, user, pass);
        Statement statement = dbConn.createStatement();
        statement.executeUpdate("CREATE DATABASE " + testDbName);
        importDB(DriverManager.getConnection(updatedUrl, user, pass), "src/main/resources/schema.sql");
        // now update the session factory
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.setProperty("hibernate.connection.url", updatedUrl);
        sFactory = cfg.buildSessionFactory();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // drop test DB
        sFactory.close();
        Statement statement = dbConn.createStatement();
        statement.executeUpdate("DROP DATABASE " + testDbName);
        dbConn.close();
    }

    @Before
    public void setUp() {
        session = sFactory.openSession();
//        session.beginTransaction();
    }

    @After
    public void tearDown() {
//        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testCategoryDao_createAndRead1000() {
        CategoryDao dao = new CategoryDao(session);
        for (int i = 1; i <= 1000; i++) {
            Category c = new Category();
            c.setName("Category " + i);
            c.setDescription("Some interesting category, one of a kind!");
            dao.save(c);
        }

        List<Category> categories = dao.getAll();
        assertNotNull(categories);
        assertTrue(categories.size() > 1000);
    }

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date asDate(String dateStr) {
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e1) {}
            
        return date;
    }


    private static void importDB(Connection db, String fileName) {
        String s = new String();
        StringBuffer sb = new StringBuffer();

        try {
            FileReader fr = new FileReader(new File(fileName));
            BufferedReader br = new BufferedReader(fr);
 
            while((s = br.readLine()) != null) {
                // skip comments
                if (s.startsWith("--"))
                    continue;
                sb.append(s);
            }
            br.close();

            // We use ";" as a delimiter for each statement
            String[] inst = sb.toString().split(";");
            Statement st = db.createStatement();

            for(int i = 0; i<inst.length; i++) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                }
            }
            // close provided connection
            db.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
