package pro_area.test_task.havriushenko.internet_market.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 200)
    private String name;
    @Column
    private double price;
    @Column(length = 500)
    private String description;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_group_id")
    private ProductGroupModel group;

    public ProductModel() {
    }

    public ProductModel(String name, double price, ProductGroupModel group) {
        this.name = name;
        this.price = price;
        this.group = group;
    }

    public ProductModel(String name, double price, ProductGroupModel group, String description) {
        this.name = name;
        this.price = price;
        this.group = group;
        this.description = description;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductGroupModel getGroup() {
        return group;
    }

    public void setGroup(ProductGroupModel group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductModel [id=" + id +
                ", name= " + name +
                ", price= " + price +
                ", group= " + group +
                ", description= " + description +
                "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + id);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductModel product = (ProductModel) o;
        return id == product.id && name == product.name;
    }

}
