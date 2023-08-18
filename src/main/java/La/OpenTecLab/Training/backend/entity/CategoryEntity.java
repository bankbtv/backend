package La.OpenTecLab.Training.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="category")
//    to add category to choices,users and history
//@JsonIgnoreProperties({"choices","user_history","Users"})
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String name;
    private String descriptionTh;
    private String descriptionEn;
//    to add category to choices,users and history
    @JsonIgnore
    @OneToMany(mappedBy = "categoryC", fetch= FetchType.LAZY)
    private List<ChoiceEntity> choices;
//    to add category to choices,users and history
    @JsonIgnore
    @OneToMany(mappedBy = "categoryU",fetch = FetchType.LAZY)
    private List<UserEntity> users;
//    to add category to choices,users and history
    @JsonIgnore
    @OneToMany(mappedBy = "categoryH", fetch= FetchType.LAZY)
    private List<HistoryEntity> historyList;
}
