package pro_area.test_task.havriushenko.internet_market.dto;

import pro_area.test_task.havriushenko.internet_market.model.ProductModel;

import java.util.HashSet;
import java.util.Set;

public class CharacteristicDto {

    private long id;
    private double weight;
    private String color;
    private double length;
    private double width;
    private double height;
    private Set<ProductModel> products = new HashSet<ProductModel>();

    public CharacteristicDto() {
    }

    public CharacteristicDto(double weight) {
        this.weight = weight;
    }

    public CharacteristicDto(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public CharacteristicDto(double weight, double length, double width, double height) {
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "СharacteristicModel[" +
                "weight=" + weight +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ']';
    }
}
