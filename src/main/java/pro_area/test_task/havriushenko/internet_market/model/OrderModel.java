package pro_area.test_task.havriushenko.internet_market.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 10)
    private String status;      //One status could be open and many statuses could be closed
    @Column(length = 30)
    private String data;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel user;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private Set<OrderInfoModel> orderInfoModels;

    public OrderModel() {
    }

    public OrderModel(UserModel user) {
        this.user = user;
    }

    public OrderModel(String status, UserModel user) {
        this.status = status;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Set<OrderInfoModel> getOrderInfoModels() {
        return orderInfoModels;
    }

    public void setOrderInfoModels(Set<OrderInfoModel> orderInfoModels) {
        this.orderInfoModels = orderInfoModels;
    }

    @Override
    public String toString() {
        return "OrderDto[" +
                " user=" + user +
                ", status='" + status + '\'' +
                ", orderInfoModels=" + orderInfoModels +
                "]";
    }
}
