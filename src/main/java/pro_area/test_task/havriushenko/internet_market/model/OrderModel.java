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
    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "order_info", joinColumns = {@JoinColumn(name = "order_id")})
    private Set<OrderInfoModel> products;

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

    public Set<OrderInfoModel> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderInfoModel> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderDto[" +
                " user=" + user +
                ", status='" + status + '\'' +
                ", products=" + products +
                "]";
    }
}
