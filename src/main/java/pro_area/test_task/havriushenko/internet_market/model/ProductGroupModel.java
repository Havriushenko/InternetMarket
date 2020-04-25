package pro_area.test_task.havriushenko.internet_market.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_group")
public class ProductGroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 50)
    private String group_name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_product_group_relation", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "product_group_id")})
    private Set<ProductModel> products = new HashSet<ProductModel>();


    public ProductGroupModel() {
    }

    public ProductGroupModel(String group_name) {
        this.group_name = group_name;
    }

    public ProductGroupModel(String group, Set<ProductModel> products) {
        this.group_name = group_name;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroup() {
        return group_name;
    }

    public void setGroup(String group_name) {
        this.group_name = group_name;
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
                "group= " + group_name +
                ']';
    }

    @Override
    public int hashCode() {
        final int prime = 25;
        int result = 1;
        result = (int) (prime * result + id);
        result = prime * result + ((group_name == null) ? 0 : group_name.hashCode());
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
        ProductGroupModel productGroup = (ProductGroupModel) o;
        return id == productGroup.id && group_name == productGroup.getGroup();
    }

}
