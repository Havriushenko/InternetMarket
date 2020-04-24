package pro_area.test_task.havriushenko.internet_market.dto;

import pro_area.test_task.havriushenko.internet_market.model.CharacteristicModel;
import pro_area.test_task.havriushenko.internet_market.model.ProductGroupModel;
import pro_area.test_task.havriushenko.internet_market.model.ProductModel;

import java.util.HashSet;
import java.util.Set;

public class ProductDto {

    private int id;
    private String name;
    private double price;
    private String description;
    private CharacteristicModel characteristic;
    private Set<ProductGroupModel> productGroup = new HashSet<ProductGroupModel>();

    public ProductDto() {
    }

    public ProductDto(String name, double price, Set<ProductGroupModel> productGroup) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
    }

    public ProductDto(String name, double price, CharacteristicModel characteristic, Set<ProductGroupModel> productGroup) {
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.productGroup = productGroup;
    }

    public ProductDto(String name, double price, Set<ProductGroupModel> productGroup, String description, CharacteristicModel characteristic) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
        this.description = description;
        this.characteristic = characteristic;
    }

    public ProductDto(String name, double price, String description, CharacteristicModel characteristic, Set<ProductGroupModel> productGroup) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.characteristic = characteristic;
        this.productGroup = productGroup;
    }

    public ProductDto(ProductModel productModel) {
        this.id = productModel.getId();
        this.name = productModel.getName();
        this.price = productModel.getPrice();
        this.description = productModel.getDescription();
//        this.characteristic = productModel.getCharacteristic();
        this.productGroup = productModel.getProductGroup();
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
                ", characteristic= " + characteristic + "]";
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
        ProductDto product = (ProductDto) o;
        return id == product.id && name == product.name;
    }
}
