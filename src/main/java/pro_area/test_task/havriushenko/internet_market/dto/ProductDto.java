package pro_area.test_task.havriushenko.internet_market.dto;

import java.util.HashSet;
import java.util.Set;

public class ProductDto {

    private int id;
    private String name;
    private double price;
    private String description;
    private CharacteristicDto characteristic;
    private Set<ProductGroupDto> productGroup = new HashSet<ProductGroupDto>();

    public ProductDto() {
    }

    public ProductDto(String name, double price, Set<ProductGroupDto> productGroup) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
    }

    public ProductDto(String name, double price, CharacteristicDto characteristic, Set<ProductGroupDto> productGroup) {
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.productGroup = productGroup;
    }

    public ProductDto(String name, double price, Set<ProductGroupDto> productGroup, String description, CharacteristicDto characteristic) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
        this.description = description;
        this.characteristic = characteristic;
    }

    public ProductDto(String name, double price, String description, CharacteristicDto characteristic, Set<ProductGroupDto> productGroup) {
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

    public Set<ProductGroupDto> getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(Set<ProductGroupDto> productGroup) {
        this.productGroup = productGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CharacteristicDto getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(CharacteristicDto characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public String toString() {
        return "ProductDto [id=" + id +
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
