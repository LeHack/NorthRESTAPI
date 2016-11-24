# Mapowanie
Mapowanie możemy w hibernate przeprowadzić przy użyciu adnotacji lub języka XML, w naszym projekcie użyliśmy konfiguracji XML. Mapowanie obiektów na rekordy w bazie danych przebiega w kilku krokach:
- Tworzymy obiekt (POJO) - obiekt musi zawierać settery i gettery oraz domyślny konstruktor (bezargumentowy), jeśli obiekt jest złożonym kluczem dla tabeli to musi również implementować interfejs Serializable oraz przesłaniać metody __equals__ i __hashcode__
- Tworzymy mapowanie w pliku z rozszerzeniem .hbm.xml
- Dodajemy plik z mapowaniem do pliku konfiguracyjnego hibernate

Poniżej przykładowe mapowanie dla tabeli product.
Klasa Product:
```java
public class Product implements Serializable {

    private Integer id;

    private Supplier supplier;

    private Category category;

    private String name;

    private String quantityPerUnit;

    private Double unitPrice;

    private Integer unitsInStock;

    private Integer unitsOnOrder;

    private Integer reorderLevel;

    private Boolean discontinued;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Integer getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(Integer unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }
}
```
Plik Product.hbm.xml:
```xml
<hibernate-mapping package="northwind.rest.app.model">
    <class name="Product" table="products">
        <id name="id" type="java.lang.Integer">
            <column name="productid"/>
            <generator class="identity"/>
        </id>
        <many-to-one name="supplier" class="Supplier" fetch="select" lazy="false">
            <column name="supplierid" not-null="true" />
        </many-to-one>
        <many-to-one name="category" class="Category" fetch="select" lazy="false">
            <column name="categoryId" not-null="true"/>
        </many-to-one>
        <property name="name" type="java.lang.String">
            <column name="productname" length="40" not-null="true"/>
        </property>
        <property name="quantityPerUnit" type="java.lang.String">
            <column name="quantityperunit" length="20"/>
        </property>
        <property name="unitPrice" type="java.lang.Double">
            <column name="unitprice"/>
        </property>
        <property name="unitsInStock" type="java.lang.Integer">
            <column name="unitsinstock"/>
        </property>
        <property name="unitsOnOrder" type="java.lang.Integer">
            <column name="unitsonorder"/>
        </property>
        <property name="reorderLevel" type="java.lang.Integer">
            <column name="reorderlevel"/>
        </property>
        <property name="discontinued" type="java.lang.Boolean">
            <column name="discontinued" not-null="true"/>
        </property>
    </class>
</hibernate-mapping>
```