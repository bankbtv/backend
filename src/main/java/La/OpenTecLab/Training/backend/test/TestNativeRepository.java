package La.OpenTecLab.Training.backend.test;

import La.OpenTecLab.Training.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestNativeRepository extends JpaRepository<UserEntity,Integer> {
    @Query(value = "SELECT first_name as firstName,Last_name as lastName from users U WHERE U.user_id = ?1", nativeQuery = true)
    TestNativeEntity findUserNameById(Integer id);
}
