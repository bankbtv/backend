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
    private String choiceNameTh;
    private String choiceNameEn;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="li4dId")
    @JsonIgnore
    private Li4dEntity li4dc;
}
