package La.OpenTecLab.Training.backend.repository;

import La.OpenTecLab.Training.backend.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findByName(String li4dName);
}
