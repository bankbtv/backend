package La.OpenTecLab.Training.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="user_history")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private JsonIgnore like;
    private JsonIgnore dislike;
    private Date date;
    @ManyToOne
    @JoinColumn(name="id")
    @JsonIgnore
    private UserEntity userH;
    @ManyToOne
    @JoinColumn(name="id")
    @JsonIgnore
    private CategoryEntity categoryH;
}
