package northwind.rest.app.model;

import java.io.Serializable;
import java.util.List;

/**
 * Class for mapping order details table.
 */
public class OrderDetails extends Base implements Serializable {

    private OrderDetailsPK id;

    private Double unitPrice;

    private Integer quantity;

    private Double discount;

    public OrderDetailsPK getId() {
        return id;
    }

    public void setId(OrderDetailsPK id) {
        this.id = id;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setFromObject(OrderDetails obj, List<String> fields) {
        super.genericSetFromObject(OrderDetails.class, obj, fields);
    }
}