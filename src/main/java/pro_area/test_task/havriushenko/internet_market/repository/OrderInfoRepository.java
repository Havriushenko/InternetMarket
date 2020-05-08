package pro_area.test_task.havriushenko.internet_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro_area.test_task.havriushenko.internet_market.model.OrderInfoModel;

import javax.transaction.Transactional;

public interface OrderInfoRepository extends JpaRepository<OrderInfoModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into order_info(order_id, product_id, quantity) values(:orderId, :productId, :qty) on duplicate key update quantity = quantity + :qty ", nativeQuery = true)
    void upsertOrderInfoByOrderIdAndProductId(@Param("orderId") int orderId, @Param("productId") int productId, @Param("qty") int quantity);

    @Modifying
    @Transactional
    @Query(value = "delete from order_info where order_id = ? and product_id = ?;", nativeQuery = true)
    void deleteByOrderIdAndProductId(int orderId, int productId);
}
