package La.OpenTecLab.Training.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdays;
    private String gender;
    private Integer categoryId;
}
