package pro_area.test_task.havriushenko.internet_market.repository;

import org.springframework.data.repository.CrudRepository;
import pro_area.test_task.havriushenko.internet_market.model.ProductModel;

public interface ProductRepository extends CrudRepository<ProductModel,Integer> {

}
