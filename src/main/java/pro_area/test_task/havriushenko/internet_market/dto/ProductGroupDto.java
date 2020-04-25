package pro_area.test_task.havriushenko.internet_market.dto;

public class ProductGroupDto {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public ProductGroupDto() {
    }

    public ProductGroupDto(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductGroup[" +
                "name= " + name + "]";
    }
}
