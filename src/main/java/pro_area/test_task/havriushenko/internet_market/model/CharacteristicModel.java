package pro_area.test_task.havriushenko.internet_market.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "characteristic")
public class CharacteristicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 30)
    private String color;
    @Column
    private double weight;
    @Column
    private double length;
    @Column
    private double height;
    @Column
    private double width;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_characteristic_relation", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "characteristic_id")})
    private Set<ProductModel> productModels = new HashSet<ProductModel>();

    public CharacteristicModel() {
    }

    public CharacteristicModel(String color) {
        this.color = color;
    }

    public CharacteristicModel(double weight) {
        this.weight = weight;
    }

    public CharacteristicModel(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public CharacteristicModel(double length, double height, double width) {
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public CharacteristicModel(String color, Set<ProductModel> productModels) {
        this.color = color;
        this.productModels = productModels;
    }

    public CharacteristicModel(double weight, Set<ProductModel> productModels) {
        this.weight = weight;
        this.productModels = productModels;
    }

    public CharacteristicModel(double length, double height, double width, Set<ProductModel> productModels) {
        this.length = length;
        this.height = height;
        this.width = width;
        this.productModels = productModels;
    }

    public CharacteristicModel(String color, double weight, double length, double height, double width, Set<ProductModel> productModels) {
        this.color = color;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.width = width;
        this.productModels = productModels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Set<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(Set<ProductModel> productModels) {
        this.productModels = productModels;
    }

    @Override
    public String toString() {
        return "CharacteristicModel[" +
                ", color='" + color +
                ", weight=" + weight +
                ", length=" + length +
                ", height=" + height +
                ", width=" + width +
                ']';
    }
}
