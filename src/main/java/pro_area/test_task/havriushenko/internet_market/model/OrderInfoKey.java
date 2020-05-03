package pro_area.test_task.havriushenko.internet_market.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderInfoKey implements Serializable {

    @Column(name = "product_id")
    private int productId;
    @Column(name = "order_id")
    private int orderId;

    public OrderInfoKey() {
    }

    public OrderInfoKey(int productId, int orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
