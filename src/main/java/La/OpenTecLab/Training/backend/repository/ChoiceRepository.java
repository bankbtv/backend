package La.OpenTecLab.Training.backend.repository;

import La.OpenTecLab.Training.backend.entity.ChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChoiceRepository extends JpaRepository<ChoiceEntity,Integer> {
}
