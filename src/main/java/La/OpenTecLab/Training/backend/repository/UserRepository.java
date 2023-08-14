package La.OpenTecLab.Training.backend.repository;

import La.OpenTecLab.Training.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmail(String userEmail);
}
