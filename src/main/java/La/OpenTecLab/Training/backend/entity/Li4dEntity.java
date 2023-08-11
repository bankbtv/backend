package La.OpenTecLab.Training.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="leader_in_4_directions")
public class Li4dEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer li4dId;
    private String li4dName;
    private String li4dDescriptionTh;
    private String li4dDescriptionEn;
    @OneToMany(mappedBy = "li4dc", fetch= FetchType.LAZY)
    private List<ChoiceEntity> choices;
    @OneToMany(mappedBy = "li4du",fetch = FetchType.LAZY)
    private List<UserEntity> user;
}
