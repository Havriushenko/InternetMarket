package pro_area.test_task.havriushenko.internet_market.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_info")
public class OrderInfoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int quantity;
    @EmbeddedId
    private OrderInfoKey orderInfoKey;
    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private OrderModel order;
    @OneToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private ProductModel product;

    public OrderInfoModel() {
    }

    public OrderInfoModel(int quantity, OrderInfoKey orderInfoKey, OrderModel order, ProductModel product) {
        this.quantity = quantity;
        this.orderInfoKey = orderInfoKey;
        this.order = order;
        this.product = product;
    }

    //    public OrderInfoModel(int quantity, ProductModel product) {
//        this.quantity = quantity;
//        this.product = product;
//    }

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

//    public ProductModel getProduct() {
//        return product;
//    }

//    public void setProduct(ProductModel product) {
//        this.product = product;
//    }


    public OrderInfoKey getOrderInfoKey() {
        return orderInfoKey;
    }

    public void setOrderInfoKey(OrderInfoKey orderInfoKey) {
        this.orderInfoKey = orderInfoKey;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderInfoDto[" +
//                "product= " + product +
                ", quantity= " + quantity +
                "]";
    }
}
