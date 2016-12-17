package northwind.rest.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Class for mapping orders table.
 */
public class Order extends Base implements Serializable {
    private static final long serialVersionUID = -8424285625793583177L;

    private Integer id;
    private Customer customer;
    private Employee employee;
    private Shipper shipVia;
    private Date orderDate, requiredDate, shippedDate;
    private Double freight;
    private String shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shipper getShipVia() {
        return shipVia;
    }

    public void setShipVia(Shipper shipVia) {
        this.shipVia = shipVia;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", employee=" + employee +
                ", shipVia=" + shipVia +
                ", orderDate=" + orderDate +
                ", requiredDate=" + requiredDate +
                ", shippedDate=" + shippedDate +
                ", freight=" + freight +
                ", shipName='" + shipName + '\'' +
                ", shipAddress='" + shipAddress + '\'' +
                ", shipCity='" + shipCity + '\'' +
                ", shipRegion='" + shipRegion + '\'' +
                ", shipPostalCode='" + shipPostalCode + '\'' +
                ", shipCountry='" + shipCountry + '\'' +
                '}';
    }

    public void setFromObject(Order obj, List<String> fields) {
        super.genericSetFromObject(Order.class, obj, fields);
    }
}
