package pro_area.test_task.havriushenko.internet_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro_area.test_task.havriushenko.internet_market.model.CharacteristicModel;

public interface CharacteristicRepository extends JpaRepository<CharacteristicModel,Integer> {
}
