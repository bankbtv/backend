package La.OpenTecLab.Training.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userImage;
    private String userAge;
    private String userGender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="li4dId")
    @JsonIgnore
    private Li4dEntity li4du;
}
