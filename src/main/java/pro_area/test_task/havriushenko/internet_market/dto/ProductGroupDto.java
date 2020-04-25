package pro_area.test_task.havriushenko.internet_market.dto;

import java.util.Set;

public class ProductGroupDto {

    private long id;
    private String group;
//    private Set<ProductDto> products = new HashSet<ProductDto>();

    public long getId() {
        return id;
    }

    public ProductGroupDto() {
    }

    public ProductGroupDto(String group) {
        this.group = group;
    }

    public ProductGroupDto(String group, Set<ProductDto> products) {
        this.group = group;
//        this.products = products;
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

//    public Set<ProductDto> getProducts() {
//        return products;
//    }

//    public void setProducts(Set<ProductDto> products) {
//        this.products = products;
//    }

    @Override
    public String toString() {
        return "ProductGroupModel[" +
                "group= " + group +
                ']';
    }
}
