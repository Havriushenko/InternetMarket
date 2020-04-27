package pro_area.test_task.havriushenko.internet_market.dto;

public class ProductGroupDto {

    private long id;
    private String group;

    public long getId() {
        return id;
    }

    public ProductGroupDto() {
    }

    public ProductGroupDto(String group) {
        this.group = group;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "ProductGroup[" +
                "group= " + group + "]";
    }
}
