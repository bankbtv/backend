package La.OpenTecLab.Training.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    private Date birthdays;
    private String gender;
    @ManyToOne
    @JoinColumn(name="id")
    @JsonIgnore
    private CategoryEntity categoryU;
    @OneToMany(mappedBy = "userH", fetch= FetchType.EAGER)
    private List<HistoryEntity> historyList;
}
