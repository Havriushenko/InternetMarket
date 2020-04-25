package pro_area.test_task.havriushenko.internet_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro_area.test_task.havriushenko.internet_market.model.ProductGroupModel;

public interface ProductGroupRerository extends JpaRepository<ProductGroupModel,Integer> {
}
