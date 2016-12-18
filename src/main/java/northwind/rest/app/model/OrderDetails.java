package northwind.rest.app.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Class for mapping order details table.
 */
public class OrderDetails extends Base implements Serializable {
    private static final long serialVersionUID = 6864114539631709169L;

    private OrderDetailsPK id;
    private Float unitPrice, discount;
    private Integer quantity;

    public OrderDetailsPK getId() {
        return id;
    }

    public void setId(OrderDetailsPK id) {
        this.id = id;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public void setFromObject(OrderDetails obj, List<String> fields) {
        super.genericSetFromObject(OrderDetails.class, obj, fields);
    }

    public void setFromMap(Map<String, Object> map) {
        super.genericSetFromMap(OrderDetails.class, map);
    }
}