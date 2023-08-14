package La.OpenTecLab.Training.backend.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    private Date birthdays;
    private String gender;
    private Integer categoryId;
}
