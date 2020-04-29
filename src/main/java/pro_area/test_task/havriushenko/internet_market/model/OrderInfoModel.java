package pro_area.test_task.havriushenko.internet_market.model;

import javax.persistence.*;

@Entity
@Table(name = "order_info")
public class OrderInfoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int quantity;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductModel productModel;

    public OrderInfoModel() {
    }

    public OrderInfoModel(int quantity, ProductModel productModel) {
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
