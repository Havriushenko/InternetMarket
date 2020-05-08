package pro_area.test_task.havriushenko.internet_market.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 10)
    private boolean status;      //One status could be open and many statuses could be closed
    @Column(length = 30)
    private String data;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private Set<OrderInfoModel> orderInfoModels = new HashSet<>();

    public OrderModel() {
    }

    public OrderModel(boolean status, UserModel user) {
        this.status = status;
        this.user = user;
    }

    public OrderModel(boolean status, String data, UserModel user, Set<OrderInfoModel> orderInfoModels) {
        this.status = status;
        this.data = data;
        this.user = user;
        this.orderInfoModels = orderInfoModels;
    }

    public OrderModel(int id, boolean status, String data, UserModel user, Set<OrderInfoModel> orderInfoModels) {
        this.id = id;
        this.status = status;
        this.data = data;
        this.user = user;
        this.orderInfoModels = orderInfoModels;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
