package northwind.rest.app.model;

import java.io.Serializable;

/**
 * Composite primary key for OrderDetails object.
 *
 * Needs to override equals and hashcode!
 */
public class OrderDetailsPK implements Serializable {

    private Order order;

    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailsPK that = (OrderDetailsPK) o;

        if (!order.equals(that.order)) return false;
        return product.equals(that.product);

    }

    @Override
    public int hashCode() {
        int result = order.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
