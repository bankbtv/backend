package La.OpenTecLab.Training.backend.repository;

import La.OpenTecLab.Training.backend.entity.Li4dEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Li4dRepository extends JpaRepository<Li4dEntity, Integer> {
    Optional<Li4dEntity> findByLi4dName(String li4dName);
}
