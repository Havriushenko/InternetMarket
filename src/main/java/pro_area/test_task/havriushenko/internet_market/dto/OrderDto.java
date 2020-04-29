package pro_area.test_task.havriushenko.internet_market.dto;

import java.util.Set;

public class OrderDto {

    private int id;
    private String status;      //One status could be open and many statuses could be closed
    private String data;
    private UserDto user;
    private Set<OrderInfoDto> products;

    public OrderDto() {
    }

    public OrderDto(UserDto user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Set<OrderInfoDto> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderInfoDto> products) {
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
