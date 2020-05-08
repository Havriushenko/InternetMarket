package pro_area.test_task.havriushenko.internet_market.dto;

import pro_area.test_task.havriushenko.internet_market.model.ProductModel;

public class OrderInfoDto {
//
    private int id;
    private int quantity;
    private ProductModel productModel;

    public OrderInfoDto() {
    }

    public OrderInfoDto(int quantity, ProductModel productModel) {
        this.quantity = quantity;
        this.productModel = productModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    @Override
    public String toString() {
        return "OrderInfoDto[" +
                "productModel= " + productModel +
                ", quantity= " + quantity +
                "]";
    }
}
