package pro_area.test_task.havriushenko.internet_market.dto;

public class ProductDto {

    private int id;
    private String name;
    private double price;
    private String description;
    private ProductGroupDto productGroup;

    public ProductDto() {
    }

    public ProductDto(String name, double price, ProductGroupDto productGroup) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
    }

    public ProductDto(String name, double price, ProductGroupDto productGroup, String description) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductGroupDto getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupDto productGroup) {
        this.productGroup = productGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product[name= " + name +
                ", price= " + price +
                ", productGroup= " + productGroup +
                ", description= " + description + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
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
        if (!super.equals(o)) {
            return false;
        }
        ProductDto product = (ProductDto) o;
        return id == product.id && name == product.name;
    }
}
