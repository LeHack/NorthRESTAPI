# Konfiguracja Hibernate

Architektura Hibernate:

![Hibernate architecture Text](http://docs.jboss.org/hibernate/orm/5.1/userguide/html_single/images/architecture/data_access_layers.svg)

W celu konfiguracji hibernate'a możemy użyć:
- hibernate.properties
- hibernate.cfg.xml
- programistycznie

My zastosowaliśmy konfigurację poprzez plik XML:
```xml
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.bytecode.use_reflection_optimizer">false</property>
        <property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
        <property name="hibernate.connection.password">admin</property>
        <property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/northrest</property>
        <property name="hibernate.connection.username">admin</property>

        <!-- JDBC connection pool (use the built-in) -->
        <property name="connection.pool_size">1</property>

        <!-- SQL dialect -->
        <property name="dialect">org.hibernate.dialect.PostgreSQL9Dialect</property>

        <!-- Enable Hibernate's automatic session context management -->
        <property name="current_session_context_class">thread</property>

        <!-- Disable the second-level cache  -->
        <!--<property name="cache.provider_class">org.hibernate.cache.internal.NoCacheProvider</property>-->

        <!-- Echo all executed SQL to stdout -->
        <property name="show_sql">true</property>
        <property name="format_sql">true</property>

        <!-- Drop and re-create the database schema on startup -->
        <property name="hbm2ddl.auto">update</property>

        <!-- mapping domain classes -->
        <mapping resource="orm/Category.hbm.xml"/>
        <mapping resource="orm/Supplier.hbm.xml"/>
        <mapping resource="orm/Product.hbm.xml"/>
        <mapping resource="orm/Shipper.hbm.xml"/>
        <mapping resource="orm/Customer.hbm.xml"/>
        <mapping resource="orm/Employee.hbm.xml"/>
        <mapping resource="orm/Order.hbm.xml"/>
        <mapping resource="orm/OrderDetails.hbm.xml"/>

    </session-factory>
</hibernate-configuration>
```

Powyżej ustawienia hibernate dla bazy PostgreSQL.

Hibernate posiada 4 główne interfejsy:
- SessionFactory - thread-safe, obiekt typu immutable, jest fabryką dla obiektów typu Session, jeden obiekt dla konkretnej bazy danych, może istnieć więcej SessionFactory jeśli utrzymujemy połączenie z większą ilością baz danych
- Session - single-thread, reprezentuje tzw. unit of work, wewnętrznie bazuje na JDBC java.sql.Connection i jest fabryką dla obiektów typu Transaction. Zawiera kolejkę zapytań SQL które mają być zsynchronizowane z bazą danych i mapę obiektów monitorowanych przez tą sesję
- Transaction - single-thread, służy do programistycznego ustawiania transakcji
- Query - obiekt do tworzenia zapytań SQL, hibernate posiada swój własny obiektowo zorientowany język zapytań HQL, można też używać SQL

### Konfiguracja SessionFactory
Tworzymy klasę pomocniczą HibernateUtil która będzie odpowiedzialna za inicjację obiektu SessionFactory i łatwy dostęp do niego (potrzebujemy SessionFactory w warstwie dostępu do obiektu DAO by tworzyć obiekty typu Session).
```java
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
```

Wykorzystujemy go później w warstwie DAO, mając SessionFactory możemy otworzyć nową sesję i zacząć transakcję, poniżej wykorzystujemy język HQL do utworzenia zapytania oraz obiekt Query do odpytania bazy danych i wyciągnięcia listy wszystkich rekordów należących do określonego obiektu.
```java
public class BaseDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public <T> List<T> getAll(String tableName, Class<T> contentType) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from " + tableName;
        Query query = session.createQuery(hql);
        List<T> items = query.list();

        session.getTransaction().commit();

        if (items == null) {
            items = Collections.emptyList();
        }
        return items;
    }
}
```