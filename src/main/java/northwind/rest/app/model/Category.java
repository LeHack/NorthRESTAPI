package northwind.rest.app.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for mapping categories table.
 */
public class Category implements Serializable {

    private Integer id;

    private String name;

    private String description;

    private String picture;

//    private Set<Product> products = new HashSet<>(0);

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
