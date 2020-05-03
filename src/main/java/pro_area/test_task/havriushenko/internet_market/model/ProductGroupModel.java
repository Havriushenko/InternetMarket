package pro_area.test_task.havriushenko.internet_market.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_group")
public class ProductGroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "group_name", length = 50)
    private String name;


    public ProductGroupModel() {
    }

    public ProductGroupModel(String name) {
        this.name = name;
    }

    public ProductGroupModel(String group, Set<ProductModel> products) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setGroup(String group_name) {
        this.name = group_name;
    }

    @Override
    public String toString() {
        return "ProductGroupModel[id=" + id +
                "group= " + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 25;
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
        ProductGroupModel productGroup = (ProductGroupModel) o;
        return id == productGroup.id && name == productGroup.getName();
    }

}
