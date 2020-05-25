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

    @Override
    public int hashCode() {
        final int prime = 45;
        int result = 1;
        result = (int) (prime * result + id);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        ProductGroupDto productGroup = (ProductGroupDto) o;
        return id == productGroup.getId() && name == productGroup.getName();
    }
}
