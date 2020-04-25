package pro_area.test_task.havriushenko.internet_market.dto;

import pro_area.test_task.havriushenko.internet_market.model.ProductModel;

import java.util.HashSet;
import java.util.Set;

public class ProductGroupDto {

    private long id;
    private String group;
    private Set<ProductModel> products = new HashSet<ProductModel>();

    public long getId() {
        return id;
    }

    public ProductGroupDto() {
    }

    public ProductGroupDto(String group) {
        this.group = group;
    }

    public ProductGroupDto(String group, Set<ProductModel> products) {
        this.group = group;
        this.products = products;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Set<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductModel> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductGroupModel[" +
                "group= " + group +
                ']';
    }
}
