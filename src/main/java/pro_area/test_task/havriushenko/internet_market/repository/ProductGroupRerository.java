package pro_area.test_task.havriushenko.internet_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro_area.test_task.havriushenko.internet_market.model.ProductGroupModel;

import java.util.Optional;

public interface ProductGroupRerository extends JpaRepository<ProductGroupModel,Integer> {
    Optional<ProductGroupModel> findByName(String name);

}
