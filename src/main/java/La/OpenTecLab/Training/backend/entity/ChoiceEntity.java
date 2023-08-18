package La.OpenTecLab.Training.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "choices")
public class ChoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer choiceId;
    private String nameTh;
    private String nameEn;
    @ManyToOne
    @JoinColumn(name="categoryId")
//    to add category to choices,users and history
//    @JsonProperty("categoryC")
    @JsonIgnore
    private CategoryEntity categoryC;
}
