package La.OpenTecLab.Training.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="user_history")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;
    private String likes;
    private String dislikes;
    private LocalDateTime dates;
    @ManyToOne
    @JoinColumn(name="userId")
    @JsonIgnore
    private UserEntity userH;
    @ManyToOne
    @JoinColumn(name="categoryId")
    @JsonIgnore
    private CategoryEntity categoryH;
}
