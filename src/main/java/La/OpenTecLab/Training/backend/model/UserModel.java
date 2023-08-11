package La.OpenTecLab.Training.backend.model;

import La.OpenTecLab.Training.backend.entity.Li4dEntity;
import lombok.Data;

@Data
public class UserModel {
    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userImage;
    private String userAge;
    private String userGender;
    private Integer li4dId;
}
