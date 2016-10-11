package northwind.rest.app.model;

import javax.persistence.*;

@Entity
@Table (name="categories")
public class Category {

    @Id
    @Column (name="categoryid")
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int id;

    @Column (name="categoryname")
    private String name;

    @Column
    private String description;

    @Column
    private String picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
