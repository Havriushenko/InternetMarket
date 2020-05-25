package pro_area.test_task.havriushenko.internet_market.dto;

import java.util.HashMap;
import java.util.Map;

public class OrderDto {

    private int id;
    private boolean status;      //One status could be open and many statuses could be closed
    private String data;
    private UserDto user;
    private Map<ProductDto, Integer> products = new HashMap<ProductDto, Integer>();

    public OrderDto() {
    }

    public OrderDto(boolean status, String data, UserDto user, Map<ProductDto, Integer> products) {
        this.status = status;
        this.data = data;
        this.user = user;
        this.products = products;
    }

    public OrderDto(int id, boolean status, String data, UserDto user, Map<ProductDto, Integer> products) {
        this.id = id;
        this.status = status;
        this.data = data;
        this.user = user;
        this.products = products;
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

    public Map<ProductDto, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductDto, Integer> products) {
        this.products = products;
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
                ", products=" + products +
                "]";
    }

    @Override
    public int hashCode() {
        final int prime = 21;
        int result = 1;
        result = (int) (prime * result + id);
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderDto order = (OrderDto) o;
        return id == order.getId() && status == order.getStatus() && user == order.getUser();
    }
}
