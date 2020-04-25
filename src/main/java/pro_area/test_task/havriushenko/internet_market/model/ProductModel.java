package pro_area.test_task.havriushenko.internet_market.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "product_characteristic_relation", joinColumns = {@JoinColumn(name = "characteristic_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private CharacteristicModel characteristic;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_product_group_relation", joinColumns = {@JoinColumn(name = "product_group_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<ProductGroupModel> productGroup = new HashSet<ProductGroupModel>();

    public ProductModel() {
    }

    public ProductModel(String name, double price, Set<ProductGroupModel> productGroup) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
    }

    public ProductModel(String name, double price, CharacteristicModel characteristic, Set<ProductGroupModel> productGroup) {
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.productGroup = productGroup;
    }

    public ProductModel(String name, double price, Set<ProductGroupModel> productGroup, String description, CharacteristicModel characteristic) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
        this.description = description;
        this.characteristic = characteristic;
    }

    public ProductModel(String name, double price, String description, CharacteristicModel characteristic, Set<ProductGroupModel> productGroup) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.characteristic = characteristic;
        this.productGroup = productGroup;
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

    public Set<ProductGroupModel> getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(Set<ProductGroupModel> productGroup) {
        this.productGroup = productGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CharacteristicModel getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(CharacteristicModel characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public String toString() {
        return "ProductModel [id=" + id +
                ", name= " + name +
                ", price= " + price +
                ", productGroup= " + productGroup +
                ", description= " + description +
                ", Perfomance= " + characteristic +
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
        if (!super.equals(o)) {
            return false;
        }
        ProductModel product = (ProductModel) o;
        return id == product.id && name == product.name;
    }

}
