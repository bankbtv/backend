package La.OpenTecLab.Training.backend.repository;

import La.OpenTecLab.Training.backend.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
