package La.OpenTecLab.Training.backend.repository;

import La.OpenTecLab.Training.backend.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity,Integer> {
}
