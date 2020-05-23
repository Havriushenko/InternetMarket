package pro_area.test_task.havriushenko.internet_market.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "order_info")
public class OrderInfoModel implements Serializable {

    @EmbeddedId
    private OrderInfoKey orderInfoKey;
    @Column
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private OrderModel order;
    @OneToOne(fetch = FetchType.LAZY)
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

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

    public void setProductId(ProductModel productId) {
        this.product = productId;
    }

    @Override
    public String toString() {
        return "OrderInfoDto[" +
//                "product= " + product +
                ", quantity= " + quantity +
                "]";
    }
}
