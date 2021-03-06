package pro_area.test_task.havriushenko.internet_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro_area.test_task.havriushenko.internet_market.model.ProductModel;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel,Integer> {
    Optional<ProductModel> findByName(String name);

}
