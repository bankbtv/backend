package La.OpenTecLab.Training.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String descriptionTh;
    private String descriptionEn;
    @OneToMany(mappedBy = "categoryC", fetch= FetchType.LAZY)
    private List<ChoiceEntity> choices;
    @OneToMany(mappedBy = "categoryU",fetch = FetchType.LAZY)
    private List<UserEntity> users;
    @OneToMany(mappedBy = "categoryH", fetch= FetchType.LAZY)
    private List<HistoryEntity> historyList;
}
