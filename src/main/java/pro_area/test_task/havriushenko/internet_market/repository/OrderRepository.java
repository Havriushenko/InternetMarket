package pro_area.test_task.havriushenko.internet_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro_area.test_task.havriushenko.internet_market.model.OrderModel;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

    Optional<OrderModel> findOrderByUserIdAndStatus(int id, boolean status);
}
