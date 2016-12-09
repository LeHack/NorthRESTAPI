package northwind.rest.app.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * Class for mapping shippers table.
 */
public class Shipper extends Base implements Serializable {

    private Integer id;
    private String companyName, phone;
    @JsonIgnore
    private Collection<Order> orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public void setFromObject(Shipper obj, List<String> fields) {
        super.genericSetFromObject(Shipper.class, obj, fields);
    }
}
