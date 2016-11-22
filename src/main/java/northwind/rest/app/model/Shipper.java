package northwind.rest.app.model;

import java.io.Serializable;

/**
 * Class for mapping shippers table.
 */
public class Shipper implements Serializable {

    private Integer id;
    private String companyName, phone;

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
}
