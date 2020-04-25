package pro_area.test_task.havriushenko.internet_market.dto;

import java.util.Set;

public class CharacteristicDto {

    private int id;
    private String color;
    private double weight;
    private double length;
    private double height;
    private double width;
//    private Set<ProductDto> productDtos = new HashSet<ProductDto>();

    public CharacteristicDto() {
    }

    public CharacteristicDto(String color) {
        this.color = color;
    }

    public CharacteristicDto(double weight) {
        this.weight = weight;
    }

    public CharacteristicDto(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public CharacteristicDto(double length, double height, double width) {
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public CharacteristicDto(String color, Set<ProductDto> productDtos) {
        this.color = color;
//        this.productDtos = this.productDtos;
    }

    public CharacteristicDto(double weight, Set<ProductDto> productDtos) {
        this.weight = weight;
//        this.productDtos = productDtos;
    }

    public CharacteristicDto(double length, double height, double width, Set<ProductDto> productDtos) {
        this.length = length;
        this.height = height;
        this.width = width;
//        this.productDtos = productDtos;
    }

    public CharacteristicDto(String color, double weight, double length, double height, double width, Set<ProductDto> productDtos) {
        this.color = color;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.width = width;
//        this.productDtos = productDtos;
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

//    public Set<ProductDto> getProductDtos() {
//        return productDtos;
//    }

//    public void setProductDtos(Set<ProductDto> productDtos) {
//        this.productDtos = productDtos;
//    }

    @Override
    public String toString() {
        return "CharacteristicDto[" +
                ", color='" + color +
                ", weight=" + weight +
                ", length=" + length +
                ", height=" + height +
                ", width=" + width +
                ']';
    }
}
