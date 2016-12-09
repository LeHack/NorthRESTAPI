package northwind.rest.app.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Class for mapping categories table.
 */
public class Category extends Base implements Serializable {

    private Integer id;
    private String name, description;
    private byte[] picture;
    @JsonIgnore
    private Collection<Product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public void setFromObject(Category obj, List<String> fields) {
        super.genericSetFromObject(Category.class, obj, fields);
    }
}
