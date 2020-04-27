package pro_area.test_task.havriushenko.internet_market.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_group")
public class ProductGroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 50)
    private String group_name;


    public ProductGroupModel() {
    }

    public ProductGroupModel(String group_name) {
        this.group_name = group_name;
    }

    public ProductGroupModel(String group, Set<ProductModel> products) {
        this.group_name = group_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return group_name;
    }

    public void setGroup(String group_name) {
        this.group_name = group_name;
    }

    @Override
    public String toString() {
        return "ProductGroupModel[id=" + id +
                "group= " + group_name + "]";
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
        return id == productGroup.id && group_name == productGroup.getName();
    }

}
