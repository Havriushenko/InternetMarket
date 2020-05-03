package pro_area.test_task.havriushenko.internet_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro_area.test_task.havriushenko.internet_market.model.UserModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);
}
